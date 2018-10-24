import sys
import numpy as np

class NeuralNetwork:
	def __init__(self):
		self.inputLayerSize = 2
		self.hiddenLayerSize = 3
		self.outputLayerSize = 1
		
		# The activation function has greatest gradients near 0, thus a small randomly
		# generated value is calculated for each weight
		self.w1 = np.random.randn(self.inputLayerSize, self.hiddenLayerSize) * 0.01
		self.w2 = np.random.randn(self.hiddenLayerSize, self.outputLayerSize ) * 0.01

	def errorGradients(self, x, y):
		# A forward pass is made to calculate the intermediary values.
		self.yHat = self.forward(x)
		
		# An element wise multiply is carried out to calculate dcdz3
		delta3 = np.multiply(-(y-self.yHat), self.sigmoidPrime(self.z3))
		
		# dcdw2 = dz3dw2 * dcdz3 
		# This dot product also deals with the sum of the errors
		# a regularisation term is added into the score function to account for 
		# any overfitting.
		dcdw2 = np.dot(self.a2.T, delta3) + (self.w2 * 1e-3)
		
		
		# dcdz2 is computed by the chain rule dcdz2 = dcdz3 * dz3da2 * da2dz2 
		delta2 = np.dot(delta3, self.w2.T)*self.sigmoidPrime(self.z2)
		dcdw1 = np.dot(x.T, delta2) + (self.w1 * 1e-3)
		
		return dcdw1, dcdw2
		
	# Trains for a default of 100000 epochs, pre-optimisation
	def train(self,x,y,n=100000):
		for i in range(n):
			#computes the gradient of the cost function with respect to the weights
			dcdw1, dcdw2 = self.errorGradients(x,y)
			#trains with a training rate of 1e-1 
			self.w1 -= dcdw1 * 1e-1
			self.w2 -= dcdw2 * 1e-1
	
	# The computation is split into parts to be used
	# in the back propogation stage.
	def forward(self,x):
		# a2 = sigmoid(x*w1)
		self.z2 = np.dot(x,self.w1)
		self.a2 = self.sigmoid(self.z2)
		
		#yhat = sigmoid(a2*w2)
		self.z3 = np.dot(self.a2,self.w2)
		yHat = self.sigmoid(self.z3)
		
		return yHat

	def sigmoid(self,z):
		return 1 / (1 + np.exp(-z))
		
	def sigmoidPrime(self,z):
		return self.sigmoid(z) * (1 - self.sigmoid(z))

def main(args):

	#Training neural network to emulate OR gate
	X = np.array([[0,0], [0,1], [1,0], [1,1]], dtype=float)
	Y = np.array([[0], [1], [1], [1]], dtype=float)

	nn = NeuralNetwork()
	print("Pre training:\n" + str(nn.forward(X)))
	
	nn.train(X,Y)
	print("Post training:\n" + str(nn.forward(X)))
	
	

if(__name__=="__main__"):
	main(sys.argv[1:])