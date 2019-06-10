# MyDiagnosis

Android application designed to provide detailed diagnosis based on observed symptoms, any Lab test results, and risk factors. Moreover, there is a Natural Language Processing feature that lets users type in a message explaining their problem and symptoms are extracted from the text. Finally, when user clicks Diagnose, a Diagnosis is generated with possible conditions listed along with their probabilities.

## APIs and Libraries used
-> Infermedica - Handles diagnosis processing with evidences given by user (https://infermedica.com/)<br/>
-> Retrofit - Used to communicate with the API to achieve application goal<br/>
-> MockK - Used to mock external dependencies for unit tests<br/>

## Architecture Components used
-> ViewModel - Used to decouple view from the data that the view is presenting. Allows data shown by the view to survive view                state changes<br/>
-> LiveData - Used to allow views to observe on data changes in the viewModels<br/>
-> Room - Used to store data locally, very easy since lot of boilerplate code is removed and trivial to define entities and<br/>             DAOs
