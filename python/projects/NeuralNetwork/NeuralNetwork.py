import sys
import numpy as np

class NeuralNetwork:
	class __Layer:
		def __init__(self, inputInfo, layerInfo, hyperperams):
			self.hyperperams = hyperperams
			self.weights = np.random.randn(layerInfo["numRows"],layerInfo["numColumns"]) * 0.01
			self.bias = np.zeros((inputInfo["numRows"] , 1))
			
		def forward(self, x):
			self.activity = x
			self.preBias = np.dot(x, self.weights)
			self.preActivation = self.preBias + self.bias
			self.yHat = self.hyperperams["activation"](self.preActivation)
			return self.yHat
		
		def backprop(self, derr):
			dcostdyHat = np.multiply(derr, self.hyperperams["activationPrime"](self.preActivation))
			dcostdw = np.dot(self.activity.T, dcostdyHat)
			dcostdb = np.multiply(self.preBias, dcostdyHat)
			
			dcostdb = np.sum(dcostdb,axis=1)
			dcostdb.shape = (dcostdb.shape[0],1)
			
			self.weights -= dcostdw * self.hyperperams["learningRate"] + \
				self.weights * self.hyperperams["regularisation"]
				
			self.bias -= dcostdb * self.hyperperams["learningRate"]
			
			delta = np.dot(dcostdyHat, self.weights.T)
			return delta
		

	def __init__(self, info):
		self.hyperperams = info["hyperperams"]
		self.inputLayer = self.__Layer(
			info["inputInfo"], 
			info["inputLayer"], 
			info["hyperperams"]
		)
		self.hiddenLayers = []
		for i in range(len(info["hiddenLayers"])):
			self.hiddenLayers.append(self.__Layer(
				info["inputInfo"], 
				info["hiddenLayers"][i], 
				info["hyperperams"]
				)
			)
		self.outputLayer = self.__Layer(
			info["inputInfo"], 
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
	
	
	

def sigmoid(x):
	return 1 / (1 + np.exp(-x))
	
def sigmoidPrime(x):
	return sigmoid(x) * (1 - sigmoid(x))
	
def main(args):
	x = np.array([[0,0],[0,1],[1,0],[1,1]])
	y = np.array([[0],[1],[1],[1]])

	hyperperams = {
		"learningRate" : 1e-1,
		"regularisation" : 1e-3,
		"epochs" : 100000,
		"activation" : sigmoid,
		"activationPrime" : sigmoidPrime
	}
	
	#Note: This strucutre is likely to overfit due to it's size,
	#      this is purely for demonstration.
	
	#This is a deep neural network, you can define additional hidden layers
	#by updating the hidden layer section.
	structureInfo = { 
		#required for calculating bias
		"inputInfo" : {
			"numRows" : x.shape[0],
			"numColumns" : x.shape[1]
		},
		"inputLayer" : {
			"numRows" : x.shape[1],
			"numColumns" : 4	
		},
		"hiddenLayers" : [
			{
				"numRows" : 4,
				"numColumns" : 3		
			},
			{
				"numRows" : 3,
				"numColumns" : 3		
			},
			{
				"numRows" : 3,
				"numColumns" : 4	
			}
		],
		"outputLayer" : {
			"numRows" : 4,
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
	
	
	
if(__name__=="__main__"):
	main(sys.argv[1:])