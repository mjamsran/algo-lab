# https://leetcode.com/problems/open-the-lock/

class Solution:
    def openLock(self, deadends: list[str], target: str) -> int:
        dead_set = set(deadends)
        visited = set("0000")
        queue = deque([("0000", 0)])  # (current_state, number_of_moves)
        
        if "0000" in dead_set:
            return -1
        
        while queue:
            current, steps = queue.popleft()
            
            # If the target is reached
            if current == target:
                return steps
            
            # Generate all possible next states
            for i in range(4):
                digit = int(current[i])
                for move in [-1, 1]:  # Turn the wheel down or up
                    new_digit = (digit + move) % 10  # Wrap around using modulo
                    new_state = current[:i] + str(new_digit) + current[i+1:]
                    
                    # Add new state to the queue if not visited or in dead ends
                    if new_state not in visited and new_state not in dead_set:
                        visited.add(new_state)
                        queue.append((new_state, steps + 1))
        
        # If we exhaust the queue without finding the target
        return -1