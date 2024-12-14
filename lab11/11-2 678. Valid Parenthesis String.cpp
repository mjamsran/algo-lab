// https://leetcode.com/problems/valid-parenthesis-string/

class Solution {
public:
    bool checkValidString(string s) {
        int cache[100][100];
        memset(cache, -1, sizeof(cache));

        auto dfs = [&](auto&& dfs, int index, int depth) {
            if(depth < 0) return 0;
            if(index == s.size()) return (int) (depth == 0);
            if(cache[index][depth] != -1) return cache[index][depth];

            if(s[index] == '(') return cache[index][depth] = dfs(dfs, index + 1, depth + 1);
            if(s[index] == ')') return cache[index][depth] = dfs(dfs, index + 1, depth - 1);
            return cache[index][depth] = dfs(dfs, index + 1, depth + 1) \
                || dfs(dfs, index + 1, depth - 1) || dfs(dfs, index + 1, depth);
        };

        return dfs(dfs, 0, 0);
    }
};