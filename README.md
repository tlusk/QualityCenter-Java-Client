# QualityCenter-Java-Client
Core idea:
Java client to hook up with Quality Center through the REST API to do custom stuff.

Scope of actions: Focus on interaction between tools, especially writing results to test lab.

It is not the goal to have an exhaustive client for the REST API. Instead it is the idea to focus on the use case:
As a test automation engineer
I want to update results in Quality center in the form of runs on a test set for certain interations of the automated tests
In order to have a consolidated view of tests executed over different tools.

# Core Architecture
As it is not the idea to write an exhaustive client the scope is limited to the most recurrent items in the context I am currently working in and my experience from past projects.

There's a client that should be "readable" and easy to use.

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
* Entity Collection (QcEntities) (can be with any QcEntity types that are defined)
* Entity Fields (Enum for API type-ahead convenience -> the type_user_01 fields and so on may be named meaningfully in the enum for a specific implementation)
  * QcDefectField
  * QcRunField
  * QcRunStepField

# Example
# Supported Actions
The API is written with automated test execution in mind.

It might strike as odd for some people to pump results into QC from automated runs. Of course you need to be pragmatic about this. Not each unit test, but the results of the functional automated tests that have merit to keep as a later reference may be stored in Quality Center to guard an aggregated view over all test efforts.

There exist options already. This API is both an exercise for myself and something that I think is useful to share.

I found the start from the guy where I forded the repo from, but I would like to stress that the core idea of this setup and the first steps in developing this client were taken by a helpful colleague at a previous employer. Thank you Stephan for handing me the groudworks for something that proved to be quite useful over the remainder of the time I spent at Fednot.
