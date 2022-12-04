// Router class to represent router instances
// Curran Fitzgerald

package com.example.networksimulator;

import java.util.*;

public class Router {
    Network parentNetwork;

    // a Router's unique identifier (e.g. R1, R2, R3, ..., Rn)
    private final String deviceName;

    // an ArrayList of Routers that represents the connections that an
    // instance of Router has to other Routers
    private final ArrayList<Router> neighbors;
    /** NOTE: Because this contains Router references, we can traverse the graph of the network with the above structure **/

    //an ArrayList of String arrays that contain a router name in index 0 and a costList in index 1
    private final ArrayList<String[]> costList;

    private RoutingTable routingTable;
    private static final double EPS = 1e-6;

    Router(String deviceName, Network parentNetwork){
        this.parentNetwork = parentNetwork;
        this.deviceName = deviceName;
        this.neighbors = new ArrayList<Router>();
        this.costList = new ArrayList<String[]>();
    }

    /** This method is for you to implement Maysam! **/
        //TODO: construct a routing table dynamically given the values of our instance variables
        public static class Edge {
            double cost;
            int from, to;
            public Edge(int from, int to, double cost) {
                this.from = from;
                this.to = to;
                this.cost = cost;
            }
        }
        public static class Node {
            int id;
            double value;

            public Node(int id, double value) {
                this.id = id;
                this.value = value;
            }

            public Node(String deviceName, String router) {
            }
        }

        private int n;
        private double[] dist;
        private Integer[] prev;
        private List<List<Edge>> graph;

        private Comparator<Node> comparator = new Comparator<Node>() {
            @Override
            public int compare(Node R1, Node R2) {
                return 0;
            }

            
            public int compare(Node nodeR1, Node nodeR2, Node nodeR3, Node nodeR4, Node nodeR5) {
                if (Math.abs(nodeR1.value - nodeR2.value - nodeR3.value - nodeR4.value - nodeR5.value) < EPS) return 0;
                return (nodeR1.value - nodeR2.value - nodeR3.value - nodeR4.value - nodeR5.value) > 0 ? +1 : -1;
            }
        };

       public void route(Router[] routers){

       }


        public RoutingTable getRoutingTable() {

            //if routing table doesn't exist, create routingTable
            if (routingTable == null) {
                route(parentNetwork.getRouters());
            }

            return routingTable;
        }


    public String getDeviceName() {
        return deviceName;
    }

    // "Removes" a connection associated with a given destination device and returns true
    // if the connection to said device was non-existent it returns false
    public boolean removeConnection(String deviceName){
        Router toRemove = null;
        for (Router neighbor : neighbors){
            if (neighbor.getDeviceName().equals(deviceName)) {
                toRemove = neighbor;
            }
        }

        if (toRemove == null) {
            //removes connection
            neighbors.removeIf(n -> (Objects.equals(n.getDeviceName(), deviceName)));

            //removes cost entry
            costList.removeIf(n -> (n[0].equals(deviceName)));

            //removes the connection and cost entry in the previously connected router
            toRemove.removeConnection(this.deviceName);
        }
        return false;
    }

    // "Connects" this router to another router if the connection does not already exist
    // returns true if operation is successful
    public boolean addConnection(Router r, int cost){
        // if the connection does not exist already (prevents infinite loop)
        if (!neighbors.contains(r)){
            //add the router reference to neighbors
            neighbors.add(r);

            //add a connection to this Router in Router r
            r.addConnection(this, cost);

            //create and enter the costEntry into costList
            String[] costEntry = {r.getDeviceName(), Integer.toString(cost)};
            costList.add(costEntry);

            //inform method caller of successful operation
            return true;
        }
        return false;
    }

    public ArrayList<Router> getNeighbors(){
        return neighbors;
    }

    public com.example.networksimulator.Node toNode(){
        return new Node(deviceName, "Router");
    }

    //gets the cost of a certain edge
    private int getCost(Router router){
        for(String[] costEntry : costList){
            if (costEntry[0].equals(router.getDeviceName())){
                return Integer.parseInt(costEntry[1]);
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        String router ="\n**** Router: "+ deviceName + " ****\n\n";
        router += "Number of Connections: " + neighbors.size() + "\n\n";

        for (String[] cost : costList){
            router += "Connected to " + cost[0] + ", cost is " + cost[1] + " milliseconds\n\n";
        }

        return router;
    }
}
