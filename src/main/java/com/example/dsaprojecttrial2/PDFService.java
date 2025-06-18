package com.example.dsaprojecttrial2;
//list ds i used here
import java.util.*;
import java.util.stream.Collectors;

//hardcoded pdfs are shown
public class PDFService {
    private List<PDF> pdfDatabase;

    public PDFService() {
        initializePDFDatabase();
    }
    //this returns the database of all websites that contain pdfs
    private void initializePDFDatabase() {
        pdfDatabase = new ArrayList<>();

        // ARRAYS
        pdfDatabase.add(new PDF(
                "Array Data Structure Complete Guide",
                "Everything about arrays: operations, 2D arrays, dynamic arrays, and optimization techniques",
                "https://www.geeksforgeeks.org/array-data-structure/",
                "Arrays", 4.8, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 85, "Beginner"
        ));
        pdfDatabase.add(new PDF(
                "Advanced Array Problem Patterns",
                "Sliding window, two pointers, and advanced array manipulation techniques",
                "https://leetcode.com/explore/learn/card/array-and-string/",
                "Arrays", 4.7, "LeetCode",
                "https://leetcode.com/static/images/LeetCode_logo_rvs.png", 120, "Intermediate"
        ));

        // ARRAYLIST
        pdfDatabase.add(new PDF(
                "ArrayList and Dynamic Arrays Guide",
                "Understanding dynamic arrays, ArrayList implementation, and memory management",
                "https://www.programiz.com/java-programming/arraylist",
                "ArrayList", 4.6, "Programiz",
                "https://www.programiz.com/sites/all/themes/programiz/assets/favicon.png", 45, "Beginner"
        ));
        pdfDatabase.add(new PDF(
                "ArrayList vs Array Performance Analysis",
                "Detailed comparison and when to use ArrayList over traditional arrays",
                "https://www.geeksforgeeks.org/array-vs-arraylist-in-java/",
                "ArrayList", 4.5, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 35, "Intermediate"
        ));

        // LINKED LISTS - Basic Variants
        pdfDatabase.add(new PDF(
                "Singly Linked List Fundamentals",
                "Introduction to singly linked lists, operations, and basic implementations",
                "https://www.geeksforgeeks.org/linked-list-set-1-introduction/",
                "Singly Linked List", 4.7, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 60, "Beginner"
        ));
        pdfDatabase.add(new PDF(
                "Doubly Linked List Guide",
                "Doubly linked list operations, advantages, and implementation details",
                "https://www.programiz.com/dsa/doubly-linked-list",
                "Doubly Linked List", 4.6, "Programiz",
                "https://www.programiz.com/sites/all/themes/programiz/assets/favicon.png", 70, "Beginner"
        ));
        pdfDatabase.add(new PDF(
                "Linked List Complete Tutorial",
                "Singly, doubly, and circular linked lists with all operations and problems",
                "https://www.geeksforgeeks.org/data-structures/linked-list/",
                "Linked List", 4.9, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 95, "Beginner"
        ));
        pdfDatabase.add(new PDF(
                "Advanced Linked List Problems",
                "Complex linked list algorithms: cycle detection, merging, and reversal techniques",
                "https://leetcode.com/explore/learn/card/linked-list/",
                "Linked List", 4.8, "LeetCode",
                "https://leetcode.com/static/images/LeetCode_logo_rvs.png", 110, "Advanced"
        ));

        // STACKS
        pdfDatabase.add(new PDF(
                "Stack Data Structure Fundamentals",
                "Stack operations, implementation, and real-world applications with examples",
                "https://www.programiz.com/dsa/stack",
                "Stack", 4.7, "Programiz",
                "https://www.programiz.com/sites/all/themes/programiz/assets/favicon.png", 55, "Beginner"
        ));
        pdfDatabase.add(new PDF(
                "Stack Applications and Problem Solving",
                "Expression evaluation, parentheses matching, and stack-based algorithms",
                "https://www.geeksforgeeks.org/stack-data-structure/",
                "Stack", 4.6, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 70, "Intermediate"
        ));

        // QUEUES - Basic Variants
        pdfDatabase.add(new PDF(
                "Queue Data Structure Complete Guide",
                "Linear queue, circular queue, and basic implementations",
                "https://www.programiz.com/dsa/queue",
                "Queue", 4.5, "Programiz",
                "https://www.programiz.com/sites/all/themes/programiz/assets/favicon.png", 60, "Beginner"
        ));
        pdfDatabase.add(new PDF(
                "Circular Queue Implementation",
                "Circular queue operations, advantages, and implementation details",
                "https://www.geeksforgeeks.org/circular-queue-set-1-introduction-and-array-implementation/",
                "Circular Queue", 4.6, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 65, "Beginner"
        ));
        pdfDatabase.add(new PDF(
                "Advanced Queue Applications",
                "BFS implementation, scheduling algorithms, and queue in system design",
                "https://www.geeksforgeeks.org/queue-data-structure/",
                "Queue", 4.6, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 75, "Intermediate"
        ));

        // DEQUES
        pdfDatabase.add(new PDF(
                "Deque Data Structure Guide",
                "Double-ended queue operations, implementations, and applications",
                "https://www.programiz.com/dsa/deque",
                "Deque", 4.7, "Programiz",
                "https://www.programiz.com/sites/all/themes/programiz/assets/favicon.png", 70, "Beginner"
        ));
        pdfDatabase.add(new PDF(
                "Advanced Deque Problems",
                "Sliding window maximum and deque-based optimizations",
                "https://leetcode.com/explore/learn/card/queue-stack/",
                "Deque", 4.6, "LeetCode",
                "https://leetcode.com/static/images/LeetCode_logo_rvs.png", 90, "Intermediate"
        ));

        // BINARY TREES
        pdfDatabase.add(new PDF(
                "Binary Tree Complete Tutorial",
                "Binary tree basics, traversals, properties, and construction algorithms",
                "https://www.geeksforgeeks.org/binary-tree-data-structure/",
                "Binary Tree", 4.9, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 130, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "Binary Search Tree Mastery",
                "BST operations, validation, and advanced BST problems with solutions",
                "https://leetcode.com/explore/learn/card/introduction-to-data-structure-binary-search-tree/",
                "Binary Tree", 4.8, "LeetCode",
                "https://leetcode.com/static/images/LeetCode_logo_rvs.png", 140, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "Advanced Tree Algorithms",
                "AVL trees, Red-Black trees, segment trees, and tree optimization techniques",
                "https://www.interviewbit.com/courses/programming/topics/tree-data-structure/",
                "Binary Tree", 4.7, "InterviewBit",
                "https://d3o2oadd0i83y8.cloudfront.net/static/img/favicon.ico", 160, "Advanced"
        ));

        // GRAPHS
        pdfDatabase.add(new PDF(
                "Graph Theory and Representation",
                "Graph basics, adjacency matrix, adjacency list, and graph properties",
                "https://www.geeksforgeeks.org/graph-data-structure-and-algorithms/",
                "Graph", 4.8, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 180, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "Graph Algorithms Comprehensive Guide",
                "All major graph algorithms: traversals, shortest paths, and connectivity",
                "https://www.hackerrank.com/domains/algorithms?filters%5Bsubdomains%5D%5B%5D=graph-theory",
                "Graph", 4.9, "HackerRank",
                "https://hrcdn.net/fcore/assets/brand/h_mark_sm-966d2b45e3.svg", 200, "Advanced"
        ));

        // DFS
        pdfDatabase.add(new PDF(
                "Depth First Search (DFS) Complete Guide",
                "DFS algorithm, implementation, applications, and problem-solving techniques",
                "https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/",
                "DFS", 4.7, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 85, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "DFS Applications and Advanced Problems",
                "Topological sorting, cycle detection, and pathfinding using DFS",
                "https://visualgo.net/en/dfsbfs",
                "DFS", 4.6, "VisuAlgo",
                "https://visualgo.net/img/favicon.png", 70, "Advanced"
        ));

        // BFS
        pdfDatabase.add(new PDF(
                "Breadth First Search (BFS) Mastery",
                "BFS algorithm, level-order traversal, and shortest path applications",
                "https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/",
                "BFS", 4.8, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 90, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "BFS in Grid Problems and Optimization",
                "Multi-source BFS, 0-1 BFS, and advanced BFS problem patterns",
                "https://leetcode.com/explore/learn/card/queue-stack/",
                "BFS", 4.7, "LeetCode",
                "https://leetcode.com/static/images/LeetCode_logo_rvs.png", 100, "Advanced"
        ));

        // HEAPS
        pdfDatabase.add(new PDF(
                "Heap Data Structure Complete Guide",
                "Min heap, max heap, heap operations, and heapify algorithm explained",
                "https://www.geeksforgeeks.org/heap-data-structure/",
                "Heap", 4.8, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 110, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "Priority Queue and Heap Applications",
                "Heap sort, priority queues, and advanced heap-based algorithms",
                "https://www.programiz.com/dsa/heap-data-structure",
                "Heap", 4.7, "Programiz",
                "https://www.programiz.com/sites/all/themes/programiz/assets/favicon.png", 95, "Intermediate"
        ));

        // HEAPIFY
        pdfDatabase.add(new PDF(
                "Heapify Algorithm and Optimization",
                "Build heap operation, heapify down/up, and time complexity analysis",
                "https://visualgo.net/en/heap",
                "Heapify", 4.6, "VisuAlgo",
                "https://visualgo.net/img/favicon.png", 65, "Advanced"
        ));

        // SORTING ALGORITHMS (Expanded with Advanced)
        pdfDatabase.add(new PDF(
                "Sorting Algorithms Complete Analysis",
                "Bubble, selection, insertion, merge, quick, and heap sort with comparisons",
                "https://www.geeksforgeeks.org/sorting-algorithms/",
                "Sorting", 4.9, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 150, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "Advanced Sorting Techniques",
                "Radix sort, counting sort, bucket sort, and external sorting methods",
                "https://visualgo.net/en/sorting",
                "Sorting", 4.7, "VisuAlgo",
                "https://visualgo.net/img/favicon.png", 120, "Advanced"
        ));
        pdfDatabase.add(new PDF(
                "Sorting Algorithm Time Complexity Guide",
                "Detailed analysis of time and space complexity for all sorting algorithms",
                "https://www.interviewbit.com/tutorial/sorting-algorithms/",
                "Sorting", 4.6, "InterviewBit",
                "https://d3o2oadd0i83y8.cloudfront.net/static/img/favicon.ico", 80, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "Shell Sort and Optimization",
                "Shell sort algorithm, implementation, and performance optimization",
                "https://www.geeksforgeeks.org/shellsort/",
                "Sorting", 4.5, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 60, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "Cocktail Sort and Bidirectional Techniques",
                "Cocktail shaker sort and bidirectional sorting algorithms",
                "https://www.programiz.com/dsa/cocktail-sort",
                "Sorting", 4.4, "Programiz",
                "https://www.programiz.com/sites/all/themes/programiz/assets/favicon.png", 50, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "Timsort Algorithm Guide",
                "Timsort implementation, hybrid sorting, and real-world usage",
                "https://www.geeksforgeeks.org/timsort/",
                "Sorting", 4.6, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 75, "Advanced"
        ));
        pdfDatabase.add(new PDF(
                "IntroSort and Adaptive Sorting",
                "IntroSort algorithm, introspective sorting, and adaptive techniques",
                "https://www.programiz.com/dsa/introsort",
                "Sorting", 4.7, "Programiz",
                "https://www.programiz.com/sites/all/themes/programiz/assets/favicon.png", 85, "Advanced"
        ));
        pdfDatabase.add(new PDF(
                "Comb Sort Optimization",
                "Comb sort algorithm, shrinkage factor, and performance analysis",
                "https://www.geeksforgeeks.org/comb-sort/",
                "Sorting", 4.5, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 65, "Intermediate"
        ));

        // SPANNING TREES
        pdfDatabase.add(new PDF(
                "Minimum Spanning Tree Algorithms",
                "Kruskal's and Prim's algorithms for finding minimum spanning trees",
                "https://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/",
                "Spanning Tree", 4.8, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 100, "Advanced"
        ));
        pdfDatabase.add(new PDF(
                "Spanning Tree Applications and Problems",
                "Network design, clustering, and real-world applications of MST",
                "https://www.hackerrank.com/domains/algorithms?filters%5Bsubdomains%5D%5B%5D=graph-theory",
                "Spanning Tree", 4.7, "HackerRank",
                "https://hrcdn.net/fcore/assets/brand/h_mark_sm-966d2b45e3.svg", 85, "Advanced"
        ));

        // DYNAMIC PROGRAMMING
        pdfDatabase.add(new PDF(
                "Dynamic Programming Mastery Guide",
                "DP patterns, memoization, tabulation, and optimization techniques",
                "https://www.interviewbit.com/courses/programming/topics/dynamic-programming/",
                "Dynamic Programming", 4.9, "InterviewBit",
                "https://d3o2oadd0i83y8.cloudfront.net/static/img/favicon.ico", 200, "Advanced"
        ));
        pdfDatabase.add(new PDF(
                "DP Problem Patterns and Solutions",
                "Classic DP problems: knapsack, LIS, LCS, and advanced DP techniques",
                "https://leetcode.com/explore/learn/card/dynamic-programming/",
                "Dynamic Programming", 4.8, "LeetCode",
                "https://leetcode.com/static/images/LeetCode_logo_rvs.png", 180, "Advanced"
        ));

        // HASHING AND HASH TABLES
        pdfDatabase.add(new PDF(
                "Hashing and Hash Tables Complete Guide",
                "Hash functions, collision resolution, and hash table implementations",
                "https://www.geeksforgeeks.org/hashing-data-structure/",
                "Hashing", 4.7, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 105, "Intermediate"
        ));

        // RECURSION AND BACKTRACKING
        pdfDatabase.add(new PDF(
                "Recursion and Backtracking Mastery",
                "Recursive thinking, backtracking algorithms, and optimization techniques",
                "https://www.interviewbit.com/courses/programming/topics/backtracking/",
                "Recursion", 4.8, "InterviewBit",
                "https://d3o2oadd0i83y8.cloudfront.net/static/img/favicon.ico", 140, "Intermediate"
        ));

        // GREEDY ALGORITHMS
        pdfDatabase.add(new PDF(
                "Greedy Algorithms and Optimization",
                "Greedy approach, activity selection, and optimization problem solving",
                "https://www.geeksforgeeks.org/greedy-algorithms/",
                "Greedy Algorithms", 4.6, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 90, "Advanced"
        ));

        // TRIES (PREFIX TREES)
        pdfDatabase.add(new PDF(
                "Trie Data Structure Complete Guide",
                "Prefix trees, auto-complete systems, and string matching algorithms",
                "https://www.geeksforgeeks.org/trie-insert-and-search/",
                "Trie", 4.7, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 75, "Advanced"
        ));

        // SEGMENT TREES
        pdfDatabase.add(new PDF(
                "Segment Tree and Range Queries",
                "Segment tree construction, range queries, and lazy propagation",
                "https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/",
                "Segment Tree", 4.8, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 120, "Advanced"
        ));

        // SUFFIX TREES
        pdfDatabase.add(new PDF(
                "Suffix Tree Data Structure",
                "Construction, applications in string matching, and substring search",
                "https://www.geeksforgeeks.org/suffix-tree-introduction/",
                "Suffix Tree", 4.7, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 130, "Advanced"
        ));

        // FENWICK TREE (BINARY INDEXED TREE)
        pdfDatabase.add(new PDF(
                "Fenwick Tree and Range Queries",
                "Binary indexed tree for point updates and range queries",
                "https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/",
                "Fenwick Tree", 4.6, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 110, "Advanced"
        ));

        // DISJOINT SET (UNION-FIND)
        pdfDatabase.add(new PDF(
                "Disjoint Set Data Structure",
                "Union-find operations, path compression, and applications",
                "https://www.geeksforgeeks.org/union-find/",
                "Disjoint Set", 4.7, "GeeksforGeeks",
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png", 90, "Intermediate"
        ));

        // GENERAL INTERVIEW PREPARATION
        pdfDatabase.add(new PDF(
                "Complete DSA Interview Preparation",
                "Comprehensive guide covering all DSA topics for technical interviews",
                "https://leetcode.com/explore/",
                "Interview Prep", 4.9, "LeetCode",
                "https://leetcode.com/static/images/LeetCode_logo_rvs.png", 300, "Intermediate"
        ));
        pdfDatabase.add(new PDF(
                "Competitive Programming Complete Guide",
                "Advanced DSA concepts for competitive programming and contests",
                "https://www.codechef.com/ide",
                "Competitive Programming", 4.7, "CodeChef",
                "https://www.codechef.com/misc/logos/codechef.svg", 250, "Advanced"
        ));
    }

    //search pdf by keyword
    public List<PDF> searchPDFs(String keyword, int limit) {
        String searchTerm = keyword.toLowerCase();
        return pdfDatabase.stream()
                .filter(pdf -> pdf.getTitle().toLowerCase().contains(searchTerm) ||
                        pdf.getDescription().toLowerCase().contains(searchTerm) ||
                        pdf.getTopic().toLowerCase().contains(searchTerm) ||
                        pdf.getSource().toLowerCase().contains(searchTerm))
                .sorted((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<PDF> getTopRatedPDFs(int limit) {
        return pdfDatabase.stream()
                .sorted((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}