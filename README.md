# QualityCenter-Java-Client
Core idea:
Java client to hook up with Quality Center through the REST API to do custom stuff.

Scope of actions: Focus on interaction between tools, especially writing results to test lab.

I'm writing this both to create something free to use for the community and as an exercise.

It is not the goal to have an exhaustive client for the REST API. Instead it is the idea to focus on the use case:
As a test automation engineer
I want to update results in Quality center in the form of runs on a test set for certain interations of the automated tests
In order to have a consolidated view of tests executed over different tools.

# Core Architecture
As it is not the idea to write an exhaustive client the scope is limited to the most recurrent items in the context I am currently working in and my experience from past projects.

There's a client that should be "readable" and easy to use.

The client has a core component (RestCallHandler) which has generic methods.

These are used in QCRestClient. This class implements readable methods. These should be fairly simple to implement as the heavy lifting is done in the RestCallHandler class.

There is a class entity to represent each item like a project or a defect.

The project, projects, domain and domains classes are managed separately.

The other ones are entities and all descend from the QcEntity class.

Included:
* Generic types
  * Projects
  * Domains
* Entity (QcEntity)
  * Defect (QcDefect)
  * Run (QcRun)
  * Run Step (QcRunStep)
* Entity Collection
  * Defects collection (QcDefects)
  * Runs collection (QcRuns)
  * Run steps collection (QcRunSteps)
* Entity Fields (Enum for API type-ahead convenience -> the type_user_01 fields and so on may be named meaningfully in the enum for a specific implementation)
  * QcDefectField
  * QcRunField
  * QcRunStepField

All entities (like QcDefect) must extend QcEntity.
All entity collections (like QcDefects) must extend QcEntities and implement QcEntitiesInterface.

This is a minimal effort to guarantee to some degree that custom extensions will work within the client's framework.

Apart from the entities and entity collections as defined by the QC REST API implementation we use another type:
the 'composits'.

The composits combine for example the QcRun and the QcRunSteps items to have more intuïtive approach.
The logic of first posting the run, retrieving the id and using that to update and bind the runsteps to the correct run is handled inside this object.
It is the goal to abstract away some cumbersome operations when working with these items.

# Example
# Supported Actions
The API is written with automated test execution in mind.

It might strike as odd for some people to pump results into QC from automated runs. Of course you need to be pragmatic about this. Not each unit test, but the results of the functional automated tests that have merit to keep as a later reference may be stored in Quality Center to guard an aggregated view over all test efforts.

Because of this viewpoint the API will not contain all entities. The focus lies on supporting the collection of results from sources outside of Quality Center. The actual test management should - in my opinion - not be handed over.

There exist options already. This API is both an exercise for myself and something that I think is useful to share.

I found the start from the guy where I forded the repo from, but I would like to stress that the core idea of this setup and the first steps in developing this client were taken by a helpful colleague at a previous employer. Thank you Stephan for handing me the groudworks for something that proved to be quite useful over the remainder of the time I spent at Fednot.
