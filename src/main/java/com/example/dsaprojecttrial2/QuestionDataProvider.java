package com.example.dsaprojecttrial2;

import java.util.*;
import java.util.stream.Collectors;

public class QuestionDataProvider {
    private static final List<Question> ALL_QUESTIONS = new ArrayList<>();

    static {
        initializeQuestions();
    }


    private static void initializeQuestions() {
        // Array Questions
        addQuestion("Two Sum",
                "Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.",
                "Easy", "Array", "arrays",
                new String[]{"Input: nums = [2,7,11,15], target = 9\nOutput: [0,1]"},
                "https://leetcode.com/problems/two-sum/", true,
                "1 <= nums.length <= 10^4\n-10^9 <= nums[i] <= 10^9",
                new String[]{"Use a HashMap for O(n) time.", "Check for edge cases."},
                "Store numbers and indices in a HashMap, check (target - num) for each.",
                "O(n)", "O(n)");

        addQuestion("Maximum Subarray",
                "Find the contiguous subarray with the largest sum in an integer array nums.",
                "Medium", "Array", "arrays",
                new String[]{"Input: nums = [-2,1,-3,4,-1,2,1,-5,4]\nOutput: 6"},
                "https://leetcode.com/problems/maximum-subarray/", true,
                "1 <= nums.length <= 10^5",
                new String[]{"Use Kadane’s algorithm.", "Handle all-negative cases."},
                "Track max sum ending at each index using Kadane’s algorithm.",
                "O(n)", "O(1)");

        addQuestion("Move Zeroes",
                "Move all 0's to the end of the array while maintaining non-zero order.",
                "Easy", "Array", "arrays",
                new String[]{"Input: nums = [0,1,0,3,12]\nOutput: [1,3,12,0,0]"},
                "https://leetcode.com/problems/move-zeroes/", true,
                "1 <= nums.length <= 10^4",
                new String[]{"Use two pointers.", "Avoid extra space."},
                "Use a pointer to place non-zeroes, fill rest with zeroes.",
                "O(n)", "O(1)");

        addQuestion("Product of Array Except Self",
                "Return an array where each element is the product of all others except itself.",
                "Medium", "Array", "arrays",
                new String[]{"Input: nums = [1,2,3,4]\nOutput: [24,12,8,6]"},
                "https://leetcode.com/problems/product-of-array-except-self/", true,
                "1 <= nums.length <= 10^5",
                new String[]{"Avoid division.", "Use two passes."},
                "Compute left and right products in separate passes.",
                "O(n)", "O(1)");

        addQuestion("Diagonal Difference",
                "Calculate the absolute difference between sums of diagonals in a square matrix.",
                "Easy", "Array", "arrays",
                new String[]{"Input: [[11,2,4],[4,5,6],[10,8,-12]]\nOutput: 15"},
                "https://www.hackerrank.com/challenges/diagonal-difference/problem", false,
                "1 <= n <= 10^3",
                new String[]{"Sum i==j and i+j==n-1.", "Single pass."},
                "Sum primary (i==j) and secondary (i+j==n-1) diagonals.",
                "O(n)", "O(1)");

        addQuestion("Compare the Triplets",
                "Compare two triplets and return an array of Alice's and Bob's scores.",
                "Easy", "Array", "arrays",
                new String[]{"Input: a = [5,6,7], b = [3,6,10]\nOutput: [1,1]"},
                "https://www.hackerrank.com/challenges/compare-the-triplets/problem", false,
                "1 <= a[i], b[i] <= 100",
                new String[]{"Compare pairs directly.", "Fixed size array."},
                "Count wins for each based on pairwise comparisons.",
                "O(1)", "O(1)");

        // Linked List Questions
        addQuestion("Reverse Linked List",
                "Reverse a singly linked list and return the new head.",
                "Easy", "Linked List", "linked list",
                new String[]{"Input: [1,2,3,4,5]\nOutput: [5,4,3,2,1]"},
                "https://leetcode.com/problems/reverse-linked-list/", true,
                "0 <= ListNode.val <= 5000",
                new String[]{"Use iterative reversal.", "Handle edge cases."},
                "Reverse pointers iteratively with three pointers.",
                "O(n)", "O(1)");

        addQuestion("Merge Two Sorted Lists",
                "Merge two sorted linked lists into one sorted list.",
                "Easy", "Linked List", "linked list",
                new String[]{"Input: [1,2,4], [1,3,4]\nOutput: [1,1,2,3,4,4]"},
                "https://leetcode.com/problems/merge-two-sorted-lists/", true,
                "0 <= ListNode.val <= 50",
                new String[]{"Use a dummy node.", "Compare nodes."},
                "Merge by comparing and linking smaller nodes.",
                "O(n+m)", "O(1)");

        addQuestion("Linked List Cycle",
                "Determine if a linked list has a cycle.",
                "Easy", "Linked List", "linked list",
                new String[]{"Input: [3,2,0,-4], pos = 1\nOutput: true"},
                "https://leetcode.com/problems/linked-list-cycle/", true,
                "0 <= ListNode.val <= 10^4",
                new String[]{"Use Floyd’s algorithm.", "Two pointers."},
                "Use slow and fast pointers; meet indicates cycle.",
                "O(n)", "O(1)");

        addQuestion("Remove Nth Node From End of List",
                "Remove the nth node from the end of a linked list.",
                "Medium", "Linked List", "linked list",
                new String[]{"Input: [1,2,3,4,5], n = 2\nOutput: [1,2,3,5]"},
                "https://leetcode.com/problems/remove-nth-node-from-end-of-list/", true,
                "1 <= n <= List length <= 30",
                new String[]{"Use two pointers.", "Handle head removal."},
                "Maintain two pointers with n gap, remove when second reaches end.",
                "O(n)", "O(1)");

        // Stack Questions
        addQuestion("Valid Parentheses",
                "Check if a string of parentheses is valid.",
                "Easy", "Stack", "stack",
                new String[]{"Input: \"()\"\nOutput: true", "Input: \"(]\"\nOutput: false"},
                "https://leetcode.com/problems/valid-parentheses/", true,
                "1 <= s.length <= 10^4",
                new String[]{"Use a stack.", "Match brackets."},
                "Push opening, pop and match with closing brackets.",
                "O(n)", "O(n)");

        addQuestion("Min Stack",
                "Design a stack with min element retrieval in O(1).",
                "Medium", "Stack", "stack",
                new String[]{"Input: push(-2), push(0), push(-3), getMin()\nOutput: -3"},
                "https://leetcode.com/problems/min-stack/", true,
                "Valid stack operations",
                new String[]{"Use two stacks.", "Sync min stack."},
                "Maintain a min stack with minimums at each level.",
                "O(1)", "O(n)");

        addQuestion("Daily Temperatures",
                "Find days until warmer temperature for each day.",
                "Medium", "Stack", "stack",
                new String[]{"Input: [73,74,75,71,69,72,76]\nOutput: [1,1,4,2,1,1,0]"},
                "https://leetcode.com/problems/daily-temperatures/", true,
                "1 <= temperatures.length <= 10^5",
                new String[]{"Use monotonic stack.", "Track indices."},
                "Use stack of indices, pop when warmer temperature found.",
                "O(n)", "O(n)");

        // Queue Questions
        addQuestion("Implement Queue using Stacks",
                "Implement a queue using two stacks.",
                "Easy", "Queue", "queue",
                new String[]{"Input: push(1), push(2), peek()\nOutput: 1"},
                "https://leetcode.com/problems/implement-queue-using-stacks/", true,
                "1 <= x <= 9",
                new String[]{"Use input/output stacks.", "Amortize cost."},
                "Push to input, move to output for dequeue.",
                "O(1) amortized", "O(n)");

        addQuestion("Sliding Window Maximum",
                "Find maximum in each window of size k.",
                "Hard", "Queue", "queue",
                new String[]{"Input: [1,3,-1,-3,5,3,6,7], k = 3\nOutput: [3,3,5,5,6,7]"},
                "https://leetcode.com/problems/sliding-window-maximum/", true,
                "1 <= k <= nums.length <= 10^5",
                new String[]{"Use deque.", "Remove outdated indices."},
                "Maintain deque of decreasing indices for max.",
                "O(n)", "O(k)");

        // Tree Questions
        addQuestion("Binary Tree Inorder Traversal",
                "Return inorder traversal of a binary tree.",
                "Easy", "Tree", "trees",
                new String[]{"Input: [1,null,2,3]\nOutput: [1,3,2]"},
                "https://leetcode.com/problems/binary-tree-inorder-traversal/", true,
                "0 <= TreeNode.val <= 100",
                new String[]{"Use stack or recursion.", "Left-root-right."},
                "Iterate with stack for left subtree, process root, right.",
                "O(n)", "O(n)");

        addQuestion("Maximum Depth of Binary Tree",
                "Find the maximum depth of a binary tree.",
                "Easy", "Tree", "trees",
                new String[]{"Input: [3,9,20,null,null,15,7]\nOutput: 3"},
                "https://leetcode.com/problems/maximum-depth-of-binary-tree/", true,
                "0 <= Tree size <= 10^4",
                new String[]{"Use DFS.", "Max of subtrees."},
                "Recurse to find max depth of left and right subtrees.",
                "O(n)", "O(h)");

        addQuestion("Validate Binary Search Tree",
                "Check if a binary tree is a valid BST.",
                "Medium", "Tree", "trees",
                new String[]{"Input: [2,1,3]\nOutput: true"},
                "https://leetcode.com/problems/validate-binary-search-tree/", true,
                "1 <= Tree size <= 10^4",
                new String[]{"Use inorder check.", "Validate ranges."},
                "Inorder traversal should be strictly increasing.",
                "O(n)", "O(h)");

        addQuestion("Lowest Common Ancestor of a Binary Tree",
                "Find LCA of two nodes in a binary tree.",
                "Medium", "Tree", "trees",
                new String[]{"Input: [3,5,1,6,2,0,8], p=5, q=1\nOutput: 3"},
                "https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/", true,
                "1 <= Tree size <= 10^5",
                new String[]{"Use recursive DFS.", "Check subtrees."},
                "Return root if both nodes in different subtrees.",
                "O(n)", "O(h)");

        // Graph Questions
        addQuestion("Number of Islands",
                "Count the number of islands in a 2D grid.",
                "Medium", "Graph", "graphs",
                new String[]{"Input: [[1,1,0],[1,1,0],[0,0,1]]\nOutput: 2"},
                "https://leetcode.com/problems/number-of-islands/", true,
                "1 <= grid.length, grid[i].length <= 300",
                new String[]{"Use DFS/BFS.", "Mark visited."},
                "Flood-fill each island with DFS, count islands.",
                "O(m*n)", "O(m*n)");

        addQuestion("Course Schedule",
                "Determine if all courses can be finished with prerequisites.",
                "Medium", "Graph", "graphs",
                new String[]{"Input: numCourses=2, prerequisites=[[1,0]]\nOutput: true"},
                "https://leetcode.com/problems/course-schedule/", true,
                "1 <= numCourses <= 2000",
                new String[]{"Detect cycles with DFS.", "Build adjacency list."},
                "Use DFS to check for cycles in prerequisite graph.",
                "O(V+E)", "O(V+E)");

        addQuestion("Journey to the Moon",
                "Find valid pairs of astronauts from different countries.",
                "Medium", "Graph", "graphs",
                new String[]{"Input: n=5, astronaut=[[0,1],[2,3],[0,4]]\nOutput: 6"},
                "https://www.hackerrank.com/challenges/journey-to-the-moon/problem", false,
                "1 <= n <= 10^5",
                new String[]{"Use Union-Find.", "Compute pairs."},
                "Group with Union-Find, calculate cross-group pairs.",
                "O(n + m)", "O(n)");

        // Hash Table Questions
        addQuestion("Group Anagrams",
                "Group strings that are anagrams together.",
                "Medium", "Hash Table", "hash table",
                new String[]{"Input: [\"eat\",\"tea\",\"tan\",\"ate\",\"nat\",\"bat\"]\nOutput: [[\"eat\",\"tea\",\"ate\"],[\"tan\",\"nat\"],[\"bat\"]]"},
                "https://leetcode.com/problems/group-anagrams/", true,
                "1 <= strs.length <= 10^4",
                new String[]{"Sort strings as keys.", "Use HashMap."},
                "Sort each string, group by sorted string in HashMap.",
                "O(n*k*log k)", "O(n*k)");

        addQuestion("LRU Cache",
                "Design an LRU cache with O(1) operations.",
                "Medium", "Hash Table", "hash table",
                new String[]{"Input: capacity=2, put(1,1), get(1)\nOutput: 1"},
                "https://leetcode.com/problems/lru-cache/", true,
                "1 <= capacity <= 3000",
                new String[]{"Use HashMap and DLL.", "Update order."},
                "Use HashMap for lookup, DLL for order, update on access.",
                "O(1)", "O(capacity)");

        // Dynamic Programming Questions
        addQuestion("Climbing Stairs",
                "Find number of ways to climb n stairs with 1 or 2 steps.",
                "Easy", "Dynamic Programming", "dynamic programming",
                new String[]{"Input: n=2\nOutput: 2"},
                "https://leetcode.com/problems/climbing-stairs/", true,
                "1 <= n <= 45",
                new String[]{"Use DP/Fibonacci.", "Optimize space."},
                "Use two variables for n-1 and n-2 ways.",
                "O(n)", "O(1)");

        addQuestion("Longest Palindromic Substring",
                "Find the longest palindromic substring in a string.",
                "Medium", "Dynamic Programming", "dynamic programming",
                new String[]{"Input: s=\"babad\"\nOutput: \"bab\""},
                "https://leetcode.com/problems/longest-palindromic-substring/", true,
                "1 <= s.length <= 1000",
                new String[]{"Expand around center.", "Check odd/even."},
                "Expand from each char/pair to find longest palindrome.",
                "O(n^2)", "O(1)");

        addQuestion("Coin Change",
                "Find minimum coins needed for a given amount.",
                "Medium", "Dynamic Programming", "dynamic programming",
                new String[]{"Input: coins=[1,2,5], amount=11\nOutput: 3"},
                "https://leetcode.com/problems/coin-change/", true,
                "1 <= amount <= 10^4",
                new String[]{"Use DP array.", "Handle unmakeable cases."},
                "DP to find min coins for each amount up to target.",
                "O(amount * coins.length)", "O(amount)");

        // Math Questions
        addQuestion("Watermelon",
                "Check if a watermelon weight can be split into two even parts.",
                "Easy", "Math", "math",
                new String[]{"Input: w=8\nOutput: YES"},
                "https://codeforces.com/problemset/problem/4/A", false,
                "1 <= w <= 100",
                new String[]{"Check even and >2.", "Simple condition."},
                "Return YES if w is even and >2, else NO.",
                "O(1)", "O(1)");

        addQuestion("Domino Piling",
                "Find number of dominoes to cover a rectangular board.",
                "Easy", "Math", "math",
                new String[]{"Input: M=2, N=4\nOutput: 4"},
                "https://codeforces.com/problemset/problem/50/A", false,
                "1 <= M, N <= 16",
                new String[]{"Divide total squares by 2.", "Check coverage."},
                "Compute (M * N) / 2.",
                "O(1)", "O(1)");

        addQuestion("Medal Photos",
                "Max photos with 3 medals of at least two types.",
                "Medium", "Math", "math",
                new String[]{"Input: g=5, s=4, b=3\nOutput: 4"},
                "https://codeforces.com/blog/entry/74345", false,
                "0 <= g, s, b <= 10^9",
                new String[]{"Use min of sums.", "Greedy approach."},
                "Compute min((g+s+b)//3, min(g+s, s+b, b+g)).",
                "O(1)", "O(1)");

        addQuestion("Theater Square",
                "Minimum flagstones to cover a rectangular square.",
                "Easy", "Math", "math",
                new String[]{"Input: n=6, m=6, a=4\nOutput: 4"},
                "https://codeforces.com/problemset/problem/1/A", false,
                "1 <= n, m, a <= 10^9",
                new String[]{"Use ceiling division.", "Multiply results."},
                "Compute ceil(n/a) * ceil(m/a).",
                "O(1)", "O(1)");

        // Greedy Questions
        addQuestion("Greedy Job Scheduling",
                "Max profit from non-overlapping jobs.",
                "Medium", "Greedy", "greedy",
                new String[]{"Input: startTime=[1,2,3,3], endTime=[3,4,5,6], profit=[50,10,40,70]\nOutput: 120"},
                "https://www.hackerrank.com/challenges/job-scheduling/problem", false,
                "1 <= n <= 10^4",
                new String[]{"Sort by end time.", "Greedy selection."},
                "Sort by end time, select non-overlapping jobs with max profit.",
                "O(n log n)", "O(n)");

        // Heap Questions
        addQuestion("Kth Largest Element in an Array",
                "Find the kth largest element in an unsorted array.",
                "Medium", "Heap", "heap",
                new String[]{"Input: nums = [3,2,1,5,6,4], k = 2\nOutput: 5"},
                "https://leetcode.com/problems/kth-largest-element-in-an-array/", true,
                "1 <= k <= nums.length <= 10^5",
                new String[]{"Use a min heap of size k.", "Maintain top k elements."},
                "Build a min heap of k elements, replace smaller with larger.",
                "O(n log k)", "O(k)");

        addQuestion("Find Median from Data Stream",
                "Find the median of a data stream as numbers are added.",
                "Hard", "Heap", "heap",
                new String[]{"Input: [1,2], addNum(3)\nOutput: 2.0"},
                "https://leetcode.com/problems/find-median-from-data-stream/", true,
                "1 <= nums.length <= 10^5",
                new String[]{"Use two heaps.", "Balance max and min heaps."},
                "Maintain max heap for lower half, min heap for upper half.",
                "O(log n)", "O(n)");

        // Sorting Questions
        addQuestion("Sort Colors",
                "Sort an array containing only 0s, 1s, and 2s.",
                "Medium", "Sorting", "sorting",
                new String[]{"Input: nums = [2,0,2,1,1,0]\nOutput: [0,0,1,1,2,2]"},
                "https://leetcode.com/problems/sort-colors/", true,
                "1 <= nums.length <= 300",
                new String[]{"Use three pointers.", "Dutch national flag algorithm."},
                "Use low, mid, high pointers to partition 0s, 1s, 2s.",
                "O(n)", "O(1)");

        addQuestion("Merge Intervals",
                "Merge overlapping intervals in a list.",
                "Medium", "Sorting", "sorting",
                new String[]{"Input: intervals = [[1,4],[4,5]]\nOutput: [[1,5]]"},
                "https://leetcode.com/problems/merge-intervals/", true,
                "1 <= intervals.length <= 10^4",
                new String[]{"Sort by start time.", "Merge overlapping."},
                "Sort intervals, merge if overlap (end >= next start).",
                "O(n log n)", "O(n)");

        // Spanning Tree Questions
        addQuestion("Kruskal's Algorithm",
                "Find the minimum spanning tree using Kruskal's algorithm.",
                "Medium", "Spanning Tree", "spanning tree",
                new String[]{"Input: edges = [[0,1,4],[1,2,3]]\nOutput: 7"},
                "https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm/", false,
                "1 <= n <= 10^3",
                new String[]{"Use Union-Find.", "Sort edges by weight."},
                "Sort edges, add to MST if no cycle with Union-Find.",
                "O(E log E)", "O(V)");

        // Trie Questions
        addQuestion("Implement Trie (Prefix Tree)",
                "Implement a trie with insert, search, and startsWith methods.",
                "Medium", "Trie", "trie",
                new String[]{"Input: insert(\"apple\"), search(\"apple\")\nOutput: true"},
                "https://leetcode.com/problems/implement-trie-prefix-tree/", true,
                "1 <= word.length <= 10^3",
                new String[]{"Use a node with children map.", "Mark end of word."},
                "Build trie with nodes, use prefix checks.",
                "O(m)", "O(ALPHABET_SIZE * m)");

        // Segment Tree Questions
        addQuestion("Range Sum Query - Immutable",
                "Given an array, compute sum of elements in a range.",
                "Easy", "Segment Tree", "segment tree",
                new String[]{"Input: nums = [1,2,3], range = [0,2]\nOutput: 6"},
                "https://leetcode.com/problems/range-sum-query-immutable/", true,
                "1 <= nums.length <= 10^4",
                new String[]{"Precompute sums.", "Use binary lifting."},
                "Build segment tree, query range sums.",
                "O(n log n)", "O(n)");

        // Suffix Tree Questions
        addQuestion("Longest Repeated Substring",
                "Find the longest repeated substring in a string.",
                "Hard", "Suffix Tree", "suffix tree",
                new String[]{"Input: s = \"banana\"\nOutput: \"ana\""},
                "https://www.geeksforgeeks.org/longest-repeated-substring/", false,
                "1 <= s.length <= 10^5",
                new String[]{"Use suffix tree.", "Find longest common prefix."},
                "Construct suffix tree, find longest repeated path.",
                "O(n log n)", "O(n)");

        // Fenwick Tree Questions
        addQuestion("Range Sum Query with Updates",
                "Compute range sums with point updates using Fenwick Tree.",
                "Medium", "Fenwick Tree", "fenwick tree",
                new String[]{"Input: nums = [1,2,3], update(1,2), range(0,2)\nOutput: 7"},
                "https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/", false,
                "1 <= nums.length <= 10^5",
                new String[]{"Use BIT for updates.", "Binary index updates."},
                "Build Fenwick Tree, update and query ranges.",
                "O(log n)", "O(n)");

        // Disjoint Set Questions
        addQuestion("Number of Connected Components",
                "Find number of connected components in an undirected graph.",
                "Medium", "Disjoint Set", "disjoint set",
                new String[]{"Input: n=5, edges=[[0,1],[1,2],[3,4]]\nOutput: 2"},
                "https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/", true,
                "1 <= n <= 10^5",
                new String[]{"Use Union-Find.", "Count sets."},
                "Union connected nodes, count final sets.",
                "O(n + m)", "O(n)");

        // Recursion Questions
        addQuestion("Fibonacci Number",
                "Calculate the nth Fibonacci number.",
                "Easy", "Recursion", "recursion",
                new String[]{"Input: n=4\nOutput: 3"},
                "https://leetcode.com/problems/fibonacci-number/", true,
                "0 <= n <= 30",
                new String[]{"Use recursive formula.", "Optimize with DP."},
                "Recurse with f(n) = f(n-1) + f(n-2).",
                "O(2^n)", "O(n)");

        // Backtracking Questions
        addQuestion("N-Queens",
                "Place N queens on an NxN chessboard so they don’t attack.",
                "Hard", "Backtracking", "backtracking",
                new String[]{"Input: n=4\nOutput: [[\".Q..\",\"...Q\",\"Q...\",\"..Q.\"]]"},
                "https://leetcode.com/problems/n-queens/", true,
                "1 <= n <= 9",
                new String[]{"Use backtracking.", "Check conflicts."},
                "Try placing queens row by row, backtrack on conflicts.",
                "O(n!)", "O(n^2)");

        // General Questions
        addQuestion("Complete DSA Interview Preparation",
                "Guide for technical interview questions across all DSA topics.",
                "Intermediate", "Interview Prep", "interview prep",
                new String[]{"Input: Various\nOutput: Solutions"},
                "https://leetcode.com/explore/", true,
                "N/A",
                new String[]{"Practice all categories.", "Review patterns."},
                "Study arrays, trees, graphs, DP, etc.",
                "O(n)", "O(1)");

        addQuestion("Competitive Programming Guide",
                "Prepare for coding contests with advanced DSA problems.",
                "Advanced", "Competitive Programming", "competitive programming",
                new String[]{"Input: Various\nOutput: Solutions"},
                "https://www.codechef.com/ide", false,
                "N/A",
                new String[]{"Focus on optimization.", "Practice under time constraints."},
                "Solve problems from multiple domains.",
                "O(n)", "O(1)");
    }


    private static void addQuestion(String title, String description, String difficulty, String category, String topic,
                                    String[] examples, String link, boolean isLeetCode,
                                    String constraints, String[] hints, String solution,
                                    String timeComplexity, String spaceComplexity) {
        Question question = new Question(title, description, difficulty, category, topic);
        question.setExamples(examples);
        question.setLeetcodeLink(link);
        question.setLeetCode(isLeetCode);
        question.setConstraints(constraints);
        question.setHints(hints);
        question.setSolution(solution);
        question.setTimeComplexity(timeComplexity);
        question.setSpaceComplexity(spaceComplexity);
        ALL_QUESTIONS.add(question);
    }

    public List<Question> getAllQuestions() {
        return new ArrayList<>(ALL_QUESTIONS);
    }

    public List<Question> filterByTopic(String topic) {
        if (topic == null || topic.trim().isEmpty()) {
            return getAllQuestions();
        }
        String lowerCaseTopic = topic.toLowerCase();
        return ALL_QUESTIONS.stream()
                .filter(q -> q.getTopic().toLowerCase().contains(lowerCaseTopic))
                .collect(Collectors.toList());
    }
}