
package com.example.dsaprojecttrial2;

import java.util.Arrays;
import java.util.List;

/**
 * Model class representing a DSA cheat sheet entry.
 */
public class CheatSheet {
    private String title;
    private String topic;
    private String category;
    private String keyConcepts;
    private String[] codeSnippets;
    private String timeComplexity;
    private String spaceComplexity;
    private String[] references;

    public CheatSheet(String title, String topic, String category, String keyConcepts, String[] codeSnippets,
                      String timeComplexity, String spaceComplexity, String[] references) {
        this.title = title;
        this.topic = topic;
        this.category = category;
        this.keyConcepts = keyConcepts;
        this.codeSnippets = codeSnippets;
        this.timeComplexity = timeComplexity;
        this.spaceComplexity = spaceComplexity;
        this.references = references;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getKeyConcepts() { return keyConcepts; }
    public void setKeyConcepts(String keyConcepts) { this.keyConcepts = keyConcepts; }

    public String[] getCodeSnippets() { return codeSnippets; }
    public void setCodeSnippets(String[] codeSnippets) { this.codeSnippets = codeSnippets; }

    public String getTimeComplexity() { return timeComplexity; }
    public void setTimeComplexity(String timeComplexity) { this.timeComplexity = timeComplexity; }

    public String getSpaceComplexity() { return spaceComplexity; }
    public void setSpaceComplexity(String spaceComplexity) { this.spaceComplexity = spaceComplexity; }

    public String[] getReferences() { return references; }
    public void setReferences(String[] references) { this.references = references; }

    // Sample dataset of cheat sheets
    public static List<CheatSheet> getSampleCheatSheets() {
        return Arrays.asList(
                new CheatSheet(
                        "Array Operations",
                        "arrays",
                        "Data Structures",
                        "Dynamic array for storing elements.\n- Access: O(1)\n- Insertion/Deletion: O(n) unless at end\n- Search: O(n) linear\nUse Case: Fixed-size data storage.",
                        new String[] {
                                "int[] arr = new int[10]; // Initialize array\narr[0] = 42; // O(1) access",
                                "for(int i = 0; i < arr.length - 1; i++) arr[i] = arr[i + 1]; // O(n) shift for deletion"
                        },
                        "Access: O(1), Search: O(n), Insertion/Deletion: O(n)",
                        "O(n)",
                        new String[] { "https://leetcode.com/problems/two-sum/", "CLRS Chapter 10" }
                ),
                new CheatSheet(
                        "Linked List Operations",
                        "linked list",
                        "Data Structures",
                        "Singly or doubly linked list.\n- Access: O(n)\n- Insertion/Deletion at head: O(1)\n- Insertion/Deletion elsewhere: O(n)\nUse Case: Dynamic data with frequent insertions.",
                        new String[] {
                                "class Node { int data; Node next; }\nNode head = new Node(); // O(1) create node",
                                "head.next = new Node(); // O(1) insert at head"
                        },
                        "Access: O(n), Insertion/Deletion: O(1) at head, O(n) elsewhere",
                        "O(n)",
                        new String[] { "https://leetcode.com/problems/reverse-linked-list/", "CLRS Chapter 10" }
                ),
                new CheatSheet(
                        "Stack Operations",
                        "stack",
                        "Data Structures",
                        "LIFO structure.\n- Push/Pop: O(1)\n- Peek: O(1)\n- Search: O(n)\nUse Case: Undo operations, expression evaluation.",
                        new String[] {
                                "Stack<Integer> stack = new Stack<>();\nstack.push(1); // O(1) push",
                                "int top = stack.pop(); // O(1) pop"
                        },
                        "Push/Pop/Peek: O(1), Search: O(n)",
                        "O(n)",
                        new String[] { "https://leetcode.com/problems/valid-parentheses/", "CLRS Chapter 10" }
                ),
                new CheatSheet(
                        "Queue Operations",
                        "queue",
                        "Data Structures",
                        "FIFO structure.\n- Enqueue/Dequeue: O(1)\n- Peek: O(1)\n- Search: O(n)\nUse Case: Task scheduling, BFS.",
                        new String[] {
                                "Queue<Integer> queue = new LinkedList<>();\nqueue.offer(1); // O(1) enqueue",
                                "int front = queue.poll(); // O(1) dequeue"
                        },
                        "Enqueue/Dequeue/Peek: O(1), Search: O(n)",
                        "O(n)",
                        new String[] { "https://leetcode.com/problems/implement-queue-using-stacks/", "CLRS Chapter 10" }
                ),
                new CheatSheet(
                        "Binary Tree Operations",
                        "trees",
                        "Data Structures",
                        "Hierarchical structure.\n- Traversal (Inorder, Preorder, Postorder): O(n)\n- Height: O(n)\n- Insertion/Deletion: O(h) where h is height\nUse Case: Hierarchical data, expression trees.",
                        new String[] {
                                "class TreeNode { int val; TreeNode left, right; }\nvoid inorder(TreeNode root) { // O(n)\n    if(root == null) return;\n    inorder(root.left);\n    System.out.print(root.val);\n    inorder(root.right);\n}",
                                "int height(TreeNode root) { // O(n)\n    if(root == null) return 0;\n    return 1 + Math.max(height(root.left), height(root.right));\n}"
                        },
                        "Traversal: O(n), Height: O(n), Insertion/Deletion: O(h)",
                        "O(h) for recursion stack",
                        new String[] { "https://leetcode.com/problems/binary-tree-inorder-traversal/", "CLRS Chapter 12" }
                ),
                new CheatSheet(
                        "Binary Search Tree Operations",
                        "trees",
                        "Data Structures",
                        "Ordered binary tree.\n- Search/Insert/Delete: O(h) where h is height\n- Balanced BST: O(log n)\n- Unbalanced: O(n)\nUse Case: Sorted data storage, dictionary.",
                        new String[] {
                                "TreeNode insert(TreeNode root, int val) { // O(h)\n    if(root == null) return new TreeNode(val);\n    if(val < root.val) root.left = insert(root.left, val);\n    else root.right = insert(root.right, val);\n    return root;\n}",
                                "TreeNode search(TreeNode root, int val) { // O(h)\n    if(root == null || root.val == val) return root;\n    return val < root.val ? search(root.left, val) : search(root.right, val);\n}"
                        },
                        "Search/Insert/Delete: O(h), Balanced: O(log n), Unbalanced: O(n)",
                        "O(h) for recursion stack",
                        new String[] { "https://leetcode.com/problems/validate-binary-search-tree/", "CLRS Chapter 12" }
                ),
                new CheatSheet(
                        "Graph Operations",
                        "graphs",
                        "Data Structures",
                        "Nodes and edges (Adjacency List/Matrix).\n- Add Edge: O(1)\n- Traversal (DFS/BFS): O(V+E)\n- Space: O(V^2) for matrix, O(V+E) for list\nUse Case: Networks, shortest paths.",
                        new String[] {
                                "List<Integer>[] adj = new List[V]; // Adjacency List\nfor(int i = 0; i < V; i++) adj[i] = new ArrayList<>();\nadj[u].add(v); // O(1) add edge",
                                "void dfs(int v, boolean[] visited) { // O(V+E)\n    visited[v] = true;\n    for(int u : adj[v]) if(!visited[u]) dfs(u, visited);\n}"
                        },
                        "Add Edge: O(1), Traversal: O(V+E)",
                        "O(V^2) for matrix, O(V+E) for list",
                        new String[] { "https://leetcode.com/problems/number-of-islands/", "CLRS Chapter 22" }
                ),
                new CheatSheet(
                        "Hash Table Operations",
                        "hash table",
                        "Data Structures",
                        "Key-value storage with hashing.\n- Insert/Get/Delete: O(1) average, O(n) worst\n- Collision Handling: Chaining, Open Addressing\nUse Case: Fast lookups, caching.",
                        new String[] {
                                "HashMap<Integer, String> map = new HashMap<>();\nmap.put(1, \"value\"); // O(1) average insert",
                                "String value = map.get(1); // O(1) average get"
                        },
                        "Insert/Get/Delete: O(1) average, O(n) worst",
                        "O(n)",
                        new String[] { "https://leetcode.com/problems/two-sum/", "CLRS Chapter 11" }
                ),
                new CheatSheet(
                        "Heap Operations",
                        "heap",
                        "Data Structures",
                        "Complete binary tree (Min/Max Heap).\n- Insert/Poll: O(log n)\n- Peek: O(1)\n- Heapify: O(n)\nUse Case: Priority queues, scheduling.",
                        new String[] {
                                "PriorityQueue<Integer> minHeap = new PriorityQueue<>();\nminHeap.offer(1); // O(log n) insert",
                                "int min = minHeap.poll(); // O(log n) remove min"
                        },
                        "Insert/Poll: O(log n), Peek: O(1), Heapify: O(n)",
                        "O(n)",
                        new String[] { "https://leetcode.com/problems/kth-largest-element-in-an-array/", "CLRS Chapter 6" }
                ),
                new CheatSheet(
                        "Trie Operations",
                        "trie",
                        "Data Structures",
                        "Tree for storing strings.\n- Insert/Search: O(m) where m is string length\n- Space: O(ALPHABET_SIZE * m * n)\nUse Case: Autocomplete, dictionary.",
                        new String[] {
                                "class TrieNode { TrieNode[] children = new TrieNode[26]; boolean isEnd; }\nvoid insert(TrieNode root, String word) { // O(m)\n    TrieNode node = root;\n    for(char c : word.toCharArray()) {\n        if(node.children[c-'a'] == null) node.children[c-'a'] = new TrieNode();\n        node = node.children[c-'a'];\n    }\n    node.isEnd = true;\n}"
                        },
                        "Insert/Search: O(m), m is string length",
                        "O(ALPHABET_SIZE * m * n)",
                        new String[] { "https://leetcode.com/problems/implement-trie-prefix-tree/", "CLRS Chapter 12" }
                ),
                new CheatSheet(
                        "Binary Search",
                        "searching",
                        "Algorithms",
                        "Search in a sorted array.\n- Requires: Sorted array\n- Divide: Halves search space\n- Use Case: Efficient lookup in sorted data.",
                        new String[] {
                                "int binarySearch(int[] arr, int target) {\n    int left = 0, right = arr.length - 1;\n    while(left <= right) {\n        int mid = left + (right - left) / 2;\n        if(arr[mid] == target) return mid;\n        else if(arr[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n} // O(log n)"
                        },
                        "O(log n) for all cases",
                        "O(1)",
                        new String[] { "https://leetcode.com/problems/binary-search/", "CLRS Chapter 2" }
                ),
                new CheatSheet(
                        "QuickSort",
                        "sorting",
                        "Algorithms",
                        "Divide-and-conquer sorting.\n- Pivot: Partitions array\n- Average: O(n log n)\n- Worst: O(n^2) with poor pivot\nUse Case: General-purpose sorting.",
                        new String[] {
                                "void quickSort(int[] arr, int low, int high) {\n    if(low < high) {\n        int pi = partition(arr, low, high);\n        quickSort(arr, low, pi - 1);\n        quickSort(arr, pi + 1, high);\n    }\n}\nint partition(int[] arr, int low, int high) {\n    int pivot = arr[high];\n    int i = low - 1;\n    for(int j = low; j < high; j++) {\n        if(arr[j] <= pivot) {\n            i++;\n            int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;\n        }\n    }\n    int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;\n    return i + 1;\n}"
                        },
                        "Average: O(n log n), Worst: O(n^2)",
                        "O(log n) for recursion stack",
                        new String[] { "https://leetcode.com/problems/sort-an-array/", "CLRS Chapter 7" }
                ),
                new CheatSheet(
                        "Graph Traversal (DFS/BFS)",
                        "graphs",
                        "Algorithms",
                        "Explore graph nodes.\n- DFS: O(V+E), recursive or stack\n- BFS: O(V+E), queue-based\nUse Case: Path finding, connected components.",
                        new String[] {
                                "void bfs(int start, List<Integer>[] adj) { // O(V+E)\n    boolean[] visited = new boolean[V];\n    Queue<Integer> queue = new LinkedList<>();\n    visited[start] = true;\n    queue.offer(start);\n    while(!queue.isEmpty()) {\n        int v = queue.poll();\n        for(int u : adj[v]) {\n            if(!visited[u]) {\n                visited[u] = true;\n                queue.offer(u);\n            }\n        }\n    }\n}"
                        },
                        "DFS/BFS: O(V+E)",
                        "O(V) for visited array and queue/stack",
                        new String[] { "https://leetcode.com/problems/number-of-islands/", "CLRS Chapter 22" }
                ),
                new CheatSheet(
                        "Dynamic Programming",
                        "dynamic programming",
                        "Algorithms",
                        "Solve problems by breaking into subproblems.\n- Memoization: Top-down\n- Tabulation: Bottom-up\nUse Case: Optimization, counting.",
                        new String[] {
                                "int fib(int n, int[] memo) { // O(n) with memoization\n    if(n <= 1) return n;\n    if(memo[n] != 0) return memo[n];\n    return memo[n] = fib(n-1, memo) + fib(n-2, memo);\n}"
                        },
                        "Depends on problem, typically O(n) to O(n^2)",
                        "O(n) for memoization table",
                        new String[] { "https://leetcode.com/problems/climbing-stairs/", "CLRS Chapter 15" }
                ),
                new CheatSheet(
                        "Greedy Algorithms",
                        "greedy",
                        "Algorithms",
                        "Make locally optimal choices.\n- Not always globally optimal\n- Use Case: Scheduling, minimum spanning tree\nExample: Activity selection.",
                        new String[] {
                                "int activitySelection(int[] start, int[] end) { // O(n log n)\n    int n = start.length;\n    int[][] activities = new int[n][2];\n    for(int i = 0; i < n; i++) activities[i] = new int[]{start[i], end[i]};\n    Arrays.sort(activities, (a, b) -> a[1] - b[1]);\n    int count = 1, lastEnd = activities[0][1];\n    for(int i = 1; i < n; i++) {\n        if(activities[i][0] >= lastEnd) {\n            count++;\n            lastEnd = activities[i][1];\n        }\n    }\n    return count;\n}"
                        },
                        "Depends on problem, typically O(n log n)",
                        "O(n) for sorting",
                        new String[] { "https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/", "CLRS Chapter 16" }
                )
        );
    }
}