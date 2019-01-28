# Image Storage Estimator

## Description
A command line interface program which can estimate the storage size needed for a set of images. The program currently supports the following types of image formats:
 - Baseline JPEG 
 - JPEG 2000 
 - Uncompressed bitmap images (BMP)

From the CLI, the user can estimate the storage size for and image using a command on the form

<pre><b>J/JPG/JP2/JPEG2000/BMP</b> width height</pre> 

__J__/__JPG__ corresponds to baseline JPEG images, __JP2__/__JPEG2000__ to JPEG 2000, and __BMP__ to uncompressed bitmaps. 

Furthermore, images can be grouped/stacked through the command 

<pre><b>G</b> i, i, ...</pre>

The program will continue to read commands until the user issues a command __Q/q__. This will trigger the program to do a final estimation of the total storage size needed for all given images/groups.

## Design 
For the assignment at hand the _strategy pattern_ seemed as an appropriate choice, since the task involved executing a specific algorithm, depending on which type of image was given by the user. 

![UML class diagram of the Image Estimator program.](/home/supertuba/repos/storage-estimator/IMGStorageEstimator/IMGStorageEstimatorUML.png)

The class _Client_ contains the main method from which the CLI is initiated. It will read from the standard input, and use the first argument to try to instantiate the correct _Image_ enumeration type. Arguments following the first are considered to be either dimension values _width_ and _height_ for the image, or index values for the grouping of images. 

With the help of the enum type the client can use a controller class _ImageContext_ to execute the proper estimation strategy for the image/group. All estimations are stored in a collection created by the client.

Once the user enters a quit command, the client will use an internal method _calculateTotalStorage_ to calculate the total storage needed.

### Image

Each Image enum has a private field _ImageStrategy strategy_ which can be reached through the class' public method _getStrategy()_. The field value is specified during the construction of the enum and made final. 

### ImageContext

ImageContext is the controller used to execute the desired strategy. The class has a method _setStrategy()_ for setting the desired strategy, and a method _execute()_ for executing it. ImageContext is used by the client during runtime.

### ImageStrategy

The abstract class _ImageStrategy_ defines the required methods for the concrete strategy classes. As of now, it only requires the concrete classes to implement the method _estimateStorage()_.

### Assumptions

The assignment description mentions that by grouping images in a stack the images can be compressed further. Thus, it was assumed that the individual estimations for the images in question could be replaced with the stack size estimation. It was also assumed that the index value for images _not_ grouped remains the same after removal of the grouped images. The group's index value was assigned in the same order as any other image. 
