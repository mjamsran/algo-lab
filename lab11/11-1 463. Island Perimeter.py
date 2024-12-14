# https://leetcode.com/problems/island-perimeter/

class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        sum_land = 0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == 1:
                    if (i == 0 or grid[i - 1][j] == 0):
                        sum_land += 1
                    if (i == len(grid) - 1 or grid[i + 1][j] == 0):
                        sum_land += 1
                    if (j == 0 or grid[i][j - 1] == 0):
                        sum_land += 1
                    if (j == len(grid[0]) - 1 or grid[i][j + 1] == 0):
                        sum_land += 1
        return sum_land
            


