/*
    * Time Complexity  = O(nlogn); n = number of tickets
    * Space Complexity = O(n)
*/
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> itinerary = new LinkedList<>();
        
        /*
            * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
            * Adjacency list:
                * {JFK: {ATL, SFO}, ATL: {JFK, SFO}, SFO: {ATL}}
        */
        Map<String, PriorityQueue<String>> graph = new HashMap<>();   // adjacency list

        Stack<String> stack = new Stack<>();    // Stack to store the current path

        for (List<String> ticket : tickets) {
            String departure = ticket.get(0); // current
            String destination = ticket.get(1); // next = target

            if (!graph.containsKey(departure)) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.add(destination);
                graph.put(departure, pq);
            } else {
                graph.get(departure).add(destination);
            }

            /*
                ALTERNATE WAY
            */
            // PriorityQueue<String> destinations = graph.get(departure);

            // if (destinations == null) {
            //     destinations = new PriorityQueue<>();
            //     graph.put(departure, destinations);
            // }

            // destinations.add(destination);
        }

        stack.push("JFK");  // Start the journey from JFK

        while (!stack.isEmpty()) {
            // Peek the current airport
            String currentAirport = stack.peek();

            // Get the priority queue of airports that can be visited from currentAirport
            PriorityQueue<String> destinations = graph.get(currentAirport);

            // If the priority queue is empty or does not exist
            if (destinations == null || destinations.isEmpty()) {
                // If there are no more tickets from the current airport, add it to the itinerary
                itinerary.addFirst(stack.pop());
            } else {
                // Add the next airport to the current path
                stack.push(destinations.poll());
            }
        }

        // Return the final itinerary
        return itinerary;
    }
}
