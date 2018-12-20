17. Letter Combinations of a Phone Number

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

注意map取index时要 -‘0’

Time: O(3 ^ n)

def letterCombinations(self, digits):
	graph = {'2':'abc', '3':'def', '4':'ghi', '5':'jkl', '6':'mno', '7':'pqrs', '8':'tuv', '9':'wxyz'}
	res = []
	if not digits:
		return res
	self.dfs(graph, digits, 0, '', res)
	return res

def dfs(self, graph, digits, index, path, res):
	if index == len(digits):
		res.append(path)
		return
	if digits[index] in graph:
		for c in graph[digits[index]]:
			self.dfs(graph, digits, index + 1, path + c, res)

public List<String> letterCombinations(String digits) {
	String[] map = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
	List<String> res = new ArrayList<>();
	if (digits.length() == 0)  return res; // important! "" -> [] Not [""]
	dfs(res, "", map, digits, 0);
	return res;
}
private void dfs(List<String> res, String tmp, String[] map, String digits, int start) {
	if (start == digits.length()) {
		res.add(tmp);
		return;
	}
	for (int i = 0; i < map[digits.charAt(start) - '0'].length(); i++) { // -'0'
		dfs(res, tmp + map[digits.charAt(start) - '0'].charAt(i), map, digits, start + 1);
	}
}