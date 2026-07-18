package Structure;

import UserPackage.City;

public class WeightedGraph {
    HashMap cities;
    int[][] graph; //weights = distances.KM

    int capacity;

    public WeightedGraph(int capacity) {
        this.capacity = capacity;
        this.cities = new HashMap(capacity);
        this.graph = new int[capacity][capacity];

        initialize();
    }

    public void edge(Object start, Object destination, int weight) {
        this.graph[((City) start).getIndex()][((City) destination).getIndex()] = weight;
        this.graph[((City) destination).getIndex()][((City) start).getIndex()] = weight;
    }
    public int getEdge(Object start, Object destination) {
        return graph[((City) start).getIndex()][((City) destination).getIndex()];
    }

    public HashMap getCities() {
        return cities;
    }

    private void initialize() {
        City rasht = new City("rasht", 0);
        City khomam = new City("khomam", 1);
        City astane = new City("astane", 2);
        City lahijan = new City("lahijan", 3);
        City siahkal = new City("siahkal", 4);
        City roodbar = new City("roodbar", 5);
        City shaft = new City("shaft", 6);
        City someesara = new City("someesara", 7);
        City fooman = new City("fooman", 8);
        City bandaranzali = new City("bandaranzali", 9);
        City langerood = new City("langerood", 10);
        City masal = new City("masal", 11);

        this.cities.insert(rasht.getName(), rasht);
        this.cities.insert(khomam.getName(), khomam);
        this.cities.insert(astane.getName(), astane);
        this.cities.insert(lahijan.getName(), lahijan);
        this.cities.insert(siahkal.getName(), siahkal);
        this.cities.insert(roodbar.getName(), roodbar);
        this.cities.insert(shaft.getName(), shaft);
        this.cities.insert(someesara.getName(), fooman);
        this.cities.insert(fooman.getName(), someesara);
        this.cities.insert(bandaranzali.getName(), bandaranzali);
        this.cities.insert(langerood.getName(), langerood);
        this.cities.insert(masal.getName(), masal);

        for (int i = 0; i < capacity; i++)
            this.graph[i][i] = 0; //each city's distance with itself is 0


        //Rasht
        edge(rasht,khomam,18); rasht.addAdjList(khomam); khomam.addAdjList(rasht);
        edge(rasht,astane,35); rasht.addAdjList(astane); astane.addAdjList(rasht);
        edge(rasht,lahijan,45); rasht.addAdjList(lahijan); lahijan.addAdjList(rasht);
        edge(rasht,siahkal,42); rasht.addAdjList(siahkal); siahkal.addAdjList(rasht);
        edge(rasht,roodbar,66); rasht.addAdjList(roodbar); roodbar.addAdjList(rasht);
        edge(rasht,shaft,29); rasht.addAdjList(shaft); shaft.addAdjList(rasht);
        edge(rasht,someesara,27); rasht.addAdjList(someesara); someesara.addAdjList(rasht);

        //Khomam
        edge(khomam,bandaranzali,30); khomam.addAdjList(bandaranzali); bandaranzali.addAdjList(khomam);

        //Astane
        edge(astane,lahijan,20); astane.addAdjList(lahijan); lahijan.addAdjList(astane);

        //Lahijan
        edge(lahijan,siahkal,16); lahijan.addAdjList(siahkal); siahkal.addAdjList(lahijan);
        edge(lahijan,langerood,16); lahijan.addAdjList(langerood); langerood.addAdjList(lahijan);

        //Siahkal
        edge(siahkal,roodbar,70); siahkal.addAdjList(roodbar); roodbar.addAdjList(siahkal);
        edge(siahkal,langerood,31); siahkal.addAdjList(langerood); langerood.addAdjList(siahkal);

        //Roodbar
        edge(roodbar,shaft,75); roodbar.addAdjList(shaft); shaft.addAdjList(roodbar);

        //Shaft
        edge(shaft,fooman,2); shaft.addAdjList(fooman); fooman.addAdjList(shaft);

        //someeSara
        edge(someesara,fooman,13); someesara.addAdjList(fooman); fooman.addAdjList(someesara);
        edge(someesara,bandaranzali,60); someesara.addAdjList(bandaranzali); bandaranzali.addAdjList(someesara);
        edge(someesara,masal,25); someesara.addAdjList(masal); masal.addAdjList(someesara);

        //Fooman
        edge(fooman,masal,32); fooman.addAdjList(masal); masal.addAdjList(fooman);

        //for others that don't have a direct path tp each other we have to find min
        //but since we need to hard code this, we don't need to write algorithms for it (such as Dijkstra Algorithm)

        edge(rasht,fooman,31);
        edge(rasht,bandaranzali,48);
        edge(rasht,langerood,61);
        edge(rasht,masal,51);
        edge(khomam,astane,52);
        edge(khomam,lahijan,63);
        edge(khomam,siahkal,65);
        edge(khomam,roodbar,84);
        edge(khomam,shaft,47);
        edge(khomam,someesara,45);
        edge(khomam,fooman,59);
        edge(khomam,langerood,79);
        edge(khomam,masal,75);
        edge(astane,siahkal,36);
        edge(astane,roodbar,101);
        edge(astane,shaft,64);
        edge(astane,someesara,62);
        edge(astane,fooman,75);
        edge(astane,bandaranzali,83);
        edge(astane,langerood,36);
        edge(astane,masal,87);
        edge(lahijan, roodbar, 86);
        edge(lahijan,shaft,74);
        edge(lahijan,someesara,72);
        edge(lahijan,fooman,76);
        edge(lahijan,bandaranzali,93);
        edge(lahijan,masal,97);
        edge(siahkal,shaft,71);
        edge(siahkal,someesara,69);
        edge(siahkal,fooman,73);
        edge(siahkal,bandaranzali,90);
        edge(siahkal,masal,94);
        edge(roodbar,someesara,90);
        edge(roodbar,fooman,77);
        edge(roodbar,bandaranzali,114);
        edge(roodbar,langerood,101);
        edge(roodbar,masal,109);
        edge(shaft,someesara,15);
        edge(shaft,bandaranzali,75);
        edge(shaft,langerood,90);
        edge(shaft,masal,45);
        edge(someesara,langerood,88);
        edge(fooman,bandaranzali,73);
        edge(fooman,langerood,92);
        edge(bandaranzali,langerood,109);
        edge(bandaranzali,masal,85);
        edge(langerood,masal,113);
    }
}
