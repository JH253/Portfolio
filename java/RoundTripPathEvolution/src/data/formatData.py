import sys
import re
from functools import reduce

def main(args):
	with open(args[0],"r") as inputFile:
		locations = re.compile("\d+\s(\d+\.\d+)\s(\d+\.\d+)")
		contents = inputFile.read()
		rawResult = locations.findall(contents)
		filtered = [str(x) + "," + str(y) for x,y in set(rawResult)]
		with open(args[1],"w") as outputFile:
			for e in filtered:
				if(filtered[-1] != e):
					outputFile.write(e + "\n")
				else:
					outputFile.write(e)

if __name__ == "__main__":
	main(sys.argv[1:])