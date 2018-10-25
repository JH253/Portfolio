import sys
import numpy as np

class NeuralNetwork:
	class __Layer:
		def __init__(self, layerInfo, hyperperams):
			self.hyperperams = hyperperams
			self.weights = np.random.randn(layerInfo["numRows"],layerInfo["numColumns"]) * 0.01
			self.bias = np.zeros((1, layerInfo["numColumns"]))
			
		def forward(self, x):
			self.activity = x
			self.preActivation = np.dot(x, self.weights) + self.bias
			self.yHat = self.hyperperams["activation"](self.preActivation)
			return self.yHat
		
		def backprop(self, derr):
			dcostdyHat = np.multiply(derr, self.hyperperams["activationPrime"](self.preActivation))
			dcostdw = np.dot(self.activity.T, dcostdyHat)
			dcostdb = dcostdyHat
			
			dcostdb = np.sum(dcostdb, axis = 0)
			dcostdb.shape = (1, dcostdb.shape[0])
			
			self.weights -= dcostdw * self.hyperperams["learningRate"] + \
				self.weights * self.hyperperams["regularisation"]
				
			self.bias -= dcostdb * self.hyperperams["learningRate"] + \
				self.bias * self.hyperperams["regularisation"]
			
			delta = np.dot(dcostdyHat, self.weights.T)
			return delta
			
		def printLayer(self):
			print("bias.shape: " + str(self.bias.shape))
			print("bias:\n" + str(self.bias) + "\n")
			print("weight.shape: " + str(self.weights.shape))
			print("weight:\n" + str(self.weights) + "\n")		

	def __init__(self, info):
		self.hyperperams = info["hyperperams"]
		self.inputLayer = self.__Layer(
			info["inputLayer"], 
			info["hyperperams"]
		)
		self.hiddenLayers = []
		for i in range(len(info["hiddenLayers"])):
			self.hiddenLayers.append(self.__Layer(
				info["hiddenLayers"][i], 
				info["hyperperams"]
				)
			)
		self.outputLayer = self.__Layer(
			info["outputLayer"], 
			info["hyperperams"]
		)
	
	def forward(self, x):
		a = self.inputLayer.forward(x)
		for l in self.hiddenLayers:
			a = l.forward(a)
		return self.outputLayer.forward(a)
	
	def backprop(self, derr):
		delta = self.outputLayer.backprop(derr)
		for l in self.hiddenLayers[::-1]:
			delta = l.backprop(delta)
		self.inputLayer.backprop(delta)
		
	def cost(self, y, yHat):
		return 1/2 * (y - yHat) ** 2
	
	def costPrime(self, y, yHat):
		return yHat - y
	
	def train(self, x, y):
		for e in range(self.hyperperams["epochs"]):
			yHat = self.forward(x)
			c = self.cost(y, yHat)
			print("iteration " + str(e)  + "/" 
				+ str(self.hyperperams["epochs"]) 
				+ " current cost: " 
				+ str(sum(c)/c.shape[0])
			)
				
			delta = self.costPrime(y, yHat)
			self.backprop(delta)
	
	def printNetwork(self):
		print("====Neural Network====")
		self.inputLayer.printLayer()
		for l in self.hiddenLayers:
			l.printLayer()
		self.outputLayer.printLayer()
		print("====\\Neural Network====")	
	
def tanh(z):
  return np.tanh(z)

def tanhPrime(a):
  return 1 - tanh(a) ** 2
	
def main(args):
	x = np.array([[0,0],[0,1],[1,0],[1,1]])
	y = np.array([[0],[1],[1],[1]])

	hyperperams = {
		"learningRate" : 1e-1,
		"regularisation" : 2e-4,
		"epochs" : 10000,
		"activation" : tanh,
		"activationPrime" : tanhPrime
	}
	#Note: This strucutre is likely to overfit due to it's size,
	#      this is purely for demonstration.
	
	#This is a deep neural network, you can define additional hidden layers
	#by updating the hidden layer section.
	structureInfo = { 
		"inputLayer" : {
			"numRows" : x.shape[1],
			"numColumns" : 3	
		},
		"hiddenLayers" : [
			
		],
		"outputLayer" : {
			"numRows" : 3,
			"numColumns" : y.shape[1]	
		},
		"hyperperams" : hyperperams
	}				
	nn = NeuralNetwork(structureInfo)
	print("input:\n" + str(x))
	print("result of forward:\n" + str(nn.forward(x)))
	print("...training...")
	nn.train(x, y)
	print("finished training")
	print("result of new forward:\n" + str(nn.forward(x)))
	
	nn.printNetwork()
	
	test = np.array([[1, 0], [0, 0], [1, 1], [1, 0], [1, 1]])
	print("testing : \n" + str(test))
	result = nn.forward(test)
	print("expected : [[1], [0], [1], [1], [1]]")
	print("acutally got: \n" + str(result))
	
if(__name__=="__main__"):
	main(sys.argv[1:])