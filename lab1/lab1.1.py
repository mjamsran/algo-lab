class Solution:
    def two_sum(self, nums, target):
        num_to_index = {}
        for i, num in enumerate(nums):
            complement = target - num
            if complement in num_to_index:
                return [num_to_index[complement], i]
            num_to_index[num] = i
        raise ValueError("No two sum solution found")

    @staticmethod
    def read_input_file(file_path):
        try:
            with open(file_path, 'r') as file:
                lines = file.readlines()
                nums = list(map(int, lines[0].strip().split(',')))
                target = int(lines[1].strip())
                return nums, target
        except FileNotFoundError:
            print("Error: File not found.")
            return None, None
        except ValueError as e:
            print(f"Error: Invalid input format. {e}")
            return None, None


file_path = "input2.txt"
nums, target = Solution.read_input_file(file_path)
if nums is not None and target is not None:
    try:
        solution = Solution()
        result = solution.two_sum(nums, target)
        print("Output:", result)
    except ValueError as e:
        print(e)