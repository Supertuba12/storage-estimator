# Image Storage Estimator

## Description
A command line interface program which can estimate the storage size needed for a set of images. The program currently supports the following types of image formats:
 - Baseline JPEG 
 - JPEG 2000 
 - Uncompressed bitmap images (BMP)

From the CLI, the user can estimate the storage size for and image using a command on the form <pre><b>J/JPG/JP2/JPEG2000/BMP</b> width height</pre> 

__J__ and __JPG__ corresponds to baseline JPEG images, __JP2__ and __JPEG2000__ to JPEG 2000, and __BMP__ to uncompressed bitmaps. 

Furthermore, images can be grouped/stacked through the command <pre> <b>G</b> i, i, ...</pre>

The program will continue to read commands until the user issues a command __Q/q__. This will trigger the program to do a final estimation of the total storage size needed for all given images/groups.

## Design 
For the assignment at hand the _strategy pattern_ seemed as an appropriate choice, since the task involved executing a specific algorithm to estimate storage size depending on which type of image was given by the user. 

### Assumptions

The assignment description mentions that by grouping images in a stack the images can be compressed further. Thus, it was assumed that the individual estimations for the images in question could be replaced with the stack size estimation. It was also assumed that the index value for images _not_ grouped remains the same after removal of the grouped images. The group's index value was assigned in the same order as any other image. 
