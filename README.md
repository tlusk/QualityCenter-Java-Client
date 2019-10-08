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

Included:
* Generic types
  * Projects
  * Domains
* Entity (QcEntity)
  * Defect (QcDefect)
* Entity Collection (QcEntities) (can be with any QcEntity types that are defined)
* Entity Fields (Enum for API type-ahead convenience -> the type_user_01 fields and so on may be named meaningfully in the enum for a specific implementation)
  * DefectField

# Example
# Supported Types and Actions
