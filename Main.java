import java.awt.*;
import java.util.*;
import  java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.*;

public class Main {
    public static void main(String[] args) {

        List<Team> teams = new ArrayList<>();
        teams.add(new Team("CSK", 14));
        teams.add(new Team("RR", 16));
        teams.add(new Team("RCB", 12));
        teams.add(new Team("MI", 6));
        teams.add(new Team("LSG", 12));
        teams.add(new Team("DC", 14));
        teams.add(new Team("KKR", 19));
        teams.add(new Team("GT", 6));
        teams.add(new Team("PBKS", 6));
        teams.add(new Team("SRH", 14));

        List<Match> remainingMatches = new ArrayList<>();
        remainingMatches.add(new Match(teams.get(1), teams.get(8)));


        List<List<Team>> a = new ArrayList<>();
        a.add(teams);
        List<List<Team>> ans = Simulation(teams, remainingMatches,0,a);
        sortlistoflists(ans);
        printresults(ans);

        List<List<Team>> qualified = checkElimination(copylistoflist(ans));
        printresults(qualified);
    }

    public static void sortlistoflists(List<List<Team>> ltt)
    {
        for(List<Team> t:ltt)
        {
            t.sort(Comparator.comparing(Team::getPoints).reversed());
        }
    }

    public static List<List<Team>>  checkElimination(List<List<Team>> ltt) {

        // Check if any team is eliminated from simulated results
        for (List<Team> lt:ltt)
        {
            while (lt.size() > 4) {
                lt.remove(4);
            }
        }
        return ltt;
    }
    public static void updatepoints(List<Team> l, Match m, boolean b) {

        int w=0;
        if (b) {

            //w = l.indexOf(m.getresult1());
             String winn = m.getresult1().name.toString();

             for (int i =0 ; i< l.size();i++) {
                 if (l.get(i).name.toString().equals(winn)) {
                     w = i;
                 }
             }
            System.out.println("Match between " + m.awayTeam.name + " " + m.homeTeam.name + " winner index is" + w
                    + "and winner is " + m.homeTeam.name);

            l.get(w).points += 2;
            System.out.println("New Points for team " + l.get(w).name + " " + l.get(w).points);

        } if(!b) {
            //w = l.indexOf(m.getresult2());

            String winn = m.getresult2().name.toString();

            for (int j =0 ; j< l.size();j++) {
                if (l.get(j).name.toString().equals(winn))
                {
                    w = j;
                }
            }
            System.out.println("Match between " + m.awayTeam.name + " " + m.homeTeam.name + " winner index is" + w
                    + "and winner is " + m.awayTeam.name);
            l.get(w).points += 2;
            System.out.println("New Points for team " + l.get(w).name + " " + l.get(w).points);
        }
    }
    ;
    public static void printresults(List<List<Team>> ltt) {


        for (List<Team> c : ltt) {
            for (Team t : c) {
                System.out.println(t.name + " " + t.points);
            }
            System.out.println(" end of one list");
        }
        System.out.println(ltt.size() + " List size ");
    }
    public static void printlist(List<Team> lt) {
        for (Team t : lt) {
                System.out.println(t.name + " " + t.points);
        }
    }

    public static List<Team> copylists(List<Team> list)
    {
        ArrayList<Team> t2 = new ArrayList<>();
        for(Team t : list)
        {
            t2.add(new Team(t.name,t.points));
        }
        return t2;
    }
    public static  List<List<Team>> copylistoflist (List<List<Team>> ltt)

    {
        List<List<Team>> t = new ArrayList<>();
        for (List<Team> lt : ltt)
        {
         t.add(copylists(lt));
        }
        return t;
    }
    public static List<List<Team>> Simulation (List<Team> lt, List<Match> lm, int index, List<List<Team>> result)
    {
        List<List<Team>> results = new ArrayList<>();

        while(index< lm.size())
        {
            for (List<Team> lit : result)
            {
                List<Team> lit1 = copylists(lit);
                updatepoints(lit1,lm.get(lm.size()-1-index),true);
                results.add(copylists(lit1));
                System.out.println("1");
                List<Team> lit2 = copylists(lit);
                updatepoints(lit2,lm.get(lm.size()-1-index),false);
                results.add(copylists(lit2));
                System.out.println("2");
            }
            result = copylistoflist(results);
            results.clear();
            System.out.println("index is " + index);
            index = index + 1;
        }
        return  result;
    }
}