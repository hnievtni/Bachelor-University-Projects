import networkx as nx  # for creating graphs
import matplotlib.pyplot as plt  # for visualizing data
import random # for generating random numbers
import os # for saving the data

class ATM:
    def __init__(self, name, priority = False, startATM = False, filled = False):
        self.name = name
        self.priority = priority
        self.startATM = startATM
        self.filled = filled
        self.refilledTime = None
        self.neighbors = []  # list of tuples (neighborName, distance)

    def __str__(self):
        return self.name

class Graph:
    def __init__(self):
        self.nodes = []  # list of ATM objects
        self.edges = {}  # dictionary of edges with weights

    def addNode(self, atm):
        if atm not in self.nodes:
            self.nodes.append(atm)
            self.edges[atm] = []

    def addEdge(self, atm1, atm2, distance):
        if atm1 in self.nodes and atm2 in self.nodes:
            self.edges[atm1].append((atm2, distance))
            self.edges[atm2].append((atm1, distance))  # for undirected graph
            atm1.neighbors.append((atm2.name, distance))  # add (atm2, distance) to atm1's neighbors
            atm2.neighbors.append((atm1.name, distance))  # add (atm1, distance) to atm2's neighbors

    def toNetworkx(self):
        # converts the graph into a Nteworkx graph for visualization
        G = nx.Graph()
        
        for atm in self.nodes:
            G.add_node(str(atm), priority = atm.priority, startATM = atm.startATM)
            
        for atm, connections in self.edges.items():
            for connectedATM, distance in connections:
                G.add_edge(str(atm), str(connectedATM), weight = distance)
        return G


# function to generate the graph
def generateGraph(numNodes, mindistance = 1, maxdistance = 20, minNeighbors = 2, maxNeighbors = 4):
    graph = Graph()

    # create ATMs
    for i in range(numNodes):
        graph.addNode(ATM(f"{i + 1}"))
        
    # add random edges ensuring each ATM has between 2 and 4 neighbors
    for atm1 in graph.nodes:
        possibleNeighbors = [
            atm2 for atm2 in graph.nodes 
            if atm2 != atm1 and 
            atm2 not in [neighbor[0] for neighbor in atm1.neighbors] and
            len(atm2.neighbors) < 4
        ]
        
        # ensure minNeighbors and maxNeighbors are valid
        if len(atm1.neighbors) < 4 and len(possibleNeighbors) > 0:
            minVal = max(0, minNeighbors - len(atm1.neighbors))  # at least 2 neighbors total
            maxVal = min(len(possibleNeighbors), maxNeighbors - len(atm1.neighbors))  # at most 4 neighbors total
            
            # randomly select neighbors
            neighbors = random.sample(possibleNeighbors, random.randint(minVal, maxVal))
        
            for atm2 in neighbors:
                distance = random.randint(mindistance, maxdistance)
                graph.addEdge(atm1, atm2, distance)
            
    return graph

# function for visualizing the graph
def visualizeGraph(graph):
    nxGraph = graph.toNetworkx()
    positions = nx.spring_layout(nxGraph, k = 5, iterations = 500) # compute positions for nodes
    
    # draw nodes with priority coloring
    colors = ['blue' if nodeData['priority'] else 'lightblue' for _, nodeData in nxGraph.nodes(data = True)]
    nx.draw(nxGraph, positions, with_labels = True, node_color = colors, node_size = 1500, font_size = 12)

    # draw the start node with a black border
    startNode = next(node for node, data in nxGraph.nodes(data = True) if data['startATM'])
    startColor = 'blue' if nxGraph.nodes[startNode]['priority'] else 'lightblue'
    nx.draw_networkx_nodes(nxGraph, positions, nodelist = [startNode], node_size = 1500, node_color = startColor, edgecolors = 'black', linewidths = 1.3)

    # draw edge labels
    nx.draw_networkx_edge_labels(nxGraph, positions, edge_labels = nx.get_edge_attributes(nxGraph, 'weight'), font_size = 8)

    plt.title("Graph Visualization")
    plt.show()
    
    exit() # end the program after displaying the graph

# printing graph's info
def storingGraphsInfo(graph):
    graphInfo = []
    
    for atm in graph.nodes:
        graphInfo.append((atm.name, atm.priority, atm.neighbors))
        
    createDataFile(graphInfo)

# find the shortest path using Dijkstra's algorithm
def findShortestPath(graph, startNode):
    G = graph.toNetworkx()
    distances, paths = nx.single_source_dijkstra(G, source = startNode.name)
    return distances, paths

# create LOG file 
def createDataFile(graphInfo):
    # writing data to a text file (start with graph's info)
    with open(outputPath, "w") as file:
        file.write("Graph's info:\n") 
        for atmInfo in graphInfo:
            file.write(f"ATM: {atmInfo[0]}, {'Priority ATM' if atmInfo[1] else 'Non Prioirty ATM'}, Neighbors list: {atmInfo[2]}\n")
        
# appending data info the LOG file    
def addData(refillData, refillStart, results, avgTime, robberyData, robberyStart, rResults, rAvgTime):
    # appending data to the text file
    with open(outputPath, "a") as file:
        # refill data
        file.write("\nRefilling process:\n") 
        file.write(f"Start ATM: {refillStart}\n")
        
        for atmInfo in refillData:
            file.write(f"ATM: {atmInfo[0]}, Refilled at time: {atmInfo[1]:.2f}, Distance: {atmInfo[2]}, Path taken: {atmInfo[3]}\n")
        
        file.write("\nRefilling results:\n")
        file.write(f"ATMs in order of refilling: {results}\n")    
        file.write(f"Average time to refill all ATMs: {avgTime:.2f} hours\n")
        
        # robbery data
        file.write("\nRobbery process:\n")
        file.write(f"Start ATM: {robberyStart}\n")
        
        for atmInfo in robberyData:
            file.write(f"ATM: {atmInfo[0]}, Refilled at time: {atmInfo[1]:.2f}, Distance: {atmInfo[2]}, Path taken: {atmInfo[3]}\n")
            
        file.write("\nRobbery results:\n")    
        file.write(f"ATMs in order of robbing: {rResults}\n")
        file.write(f"Average time to rob all ATMs: {rAvgTime:.2f} hours")
        
# reading and printing the LOG file
def readData():
    # open the file in read mode with UTF-8 encoding
    with open(outputPath, 'r', encoding = 'utf-8') as file:
        for line in file:
            # remove only the trailing newline character and print the line
            print("")
            print(line.rstrip())

# simulating the delivery and robbery
def process(graph, startATM):
    refillData = [] # refilling data for storing in a LOG file

    current = startATM # refilling current node
    time = 0  # total time for delivery (in hours)
    results = []  # store the delivery results
                                                                       
    priority = [atm for atm in graph.nodes if atm.priority]
    nonPriority = [atm for atm in graph.nodes if not atm.priority]

    while priority:
        # run dijkstra from the current node
        distances, paths = findShortestPath(graph, current)
        next = min(priority, key = lambda atm: distances[atm.name]) # nearest neighbor

        arrivalTime = distances[next.name] / 60 # assuming 60 km/h
        time += arrivalTime + 0.5 # time needed for refilling (30 minutes)
        
        next.filled = True
        next.refilledTime = time
        current = next
        
        refillData.append((next.name, time, distances[next.name], paths[next.name])) # add to the refill data
        results.append(next.name) # add the visited node to the list
        priority.remove(next) #remove from the visited list
        
    while nonPriority:
        # run dijkstra from the current node
        distances, paths = findShortestPath(graph, current)
        next = min(nonPriority, key = lambda atm: distances[atm.name]) # nearest neighbor

        arrivalTime = distances[next.name] / 60 # assuming 60 km/h
        time += arrivalTime + 0.5 # time needed for refilling (30 minutes)
                
        next.filled = True 
        next.refilledTime = time
        current = next
        
        refillData.append((next.name, time, distances[next.name], paths[next.name])) # add to the refill data
        results.append(next.name) # add the visited node to the list
        nonPriority.remove(next) # remove from the visited list
    
    robberyData = [] # robbery data for storing in a LOG file
    
    rCurrent = random.choice(graph.nodes) # robbery start node
    rTime = 3 # total time for robbing (in hours) (starts at time + 3)
    rResults = [] # store robbed ATMs  
    
    filled = [atm for atm in graph.nodes if atm.filled and (atm.refilledTime <= rTime)]
    
    while filled:
        filled = [atm for atm in graph.nodes if atm.filled and (atm.refilledTime <= rTime)] # update the filled list
        
        # run dijkstra from the current node
        distances, paths = findShortestPath(graph, rCurrent)
        rNext = min(filled, key = lambda atm: distances[atm.name]) # nearest neighbor
        
        arrivalTime = distances[rNext.name] / 100 # assuming 100 km/h
        rTime += arrivalTime + 1 # time needed for robbing an ATM (1 hour)
        
        # when refilling process ends, robbery should stop
        if (rTime >= time):
            break
            
        rCurrent = rNext
        rNext.filled = False
        
        robberyData.append((rNext.name, rTime, distances[rNext.name], paths[rNext.name])) # add to the robbery data
        rResults.append(rNext.name) # add the robbed node to the list
        filled.remove(rNext) #remove from the filled list
              
    # calculate and display average time
    avgTime = time / len(results)
    # calculate and display average time
    if len(rResults) > 0:
        rAvgTime = rTime / len(rResults)
    else:
        rAvgTime = 0
    
    addData(refillData, startATM, results, avgTime, robberyData, rCurrent, rResults, rAvgTime)

      
# creating a graph with 5-15 nodes
graph = generateGraph(random.randint(5, 15))

# assign priority to 2-4 random ATMs
priorityNodes = random.sample(graph.nodes, random.randint(2, 4))
for atm in priorityNodes:
    atm.priority = True

# start the delivery process from a random ATM
startATM = random.choice(graph.nodes)
startATM.startATM = True

# fefine the path for the LOG file
currentDir = os.path.dirname(os.path.abspath(__file__))
outputPath = os.path.join(currentDir, "LOG.txt")

# storing graph's info
storingGraphsInfo(graph)

# refilling and robbery process
process(graph, startATM)

# printing LOG file
readData()

# visualize the graph
visualizeGraph(graph)