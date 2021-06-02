# Towards Lifelong Cultural Heritage Experience 
## Table of contents
1. [General Description](#general-description)
2. [Details](#details)
3. [Material](#material)
4. [Delivery](#delivery)

## 1. General description:
The assignment is planned for teams of two. It will replicate a study that was published at PUC (reference below + appears at the data directory) – so you may follow the ideas there – or better – be more creative<sup>[1]</sup>.  
You will get visit logs and will be asked to do two things:
* analyze the logs and present information to the museum curator
* create a “play back” system that will allow re-constructing of a museum visit using the time-tagged data in the log.

Consider following the Model-View-Controller (MVC) design/architectural pattern.
Language/development environment – whatever you like.  

## 2. Details
You are requested to develop a Web-based system that, given a museum floor plan and a set of visit logs of groups of different sizes, will enable its user to:
1. “play back” a visit of a selected group (or individual) – see how they moved around in the museum between exhibits, when it is possible to speed up the visit.
2. Create a visit summary for a selected group/individual
    * Where they visited
    * How much time they spent
    * What presentations they watched
    * Did they enjoy
        * Stayed more than the average group
        * Watched presentations more than average
        * Rated presentation higher than the average
        * Did not interrupt the presentations
3. Present (visualize) statistics about the museum visit:
    * Analysis of how many visitors per hour in general
    * How many visitors per room per hour?
    * Attraction power (how many/proportion of visitors to the site visited a specific POI?) and holding power (how much time/relative time did visitors spend at a POI once they got there) of exhibits.

For the project you need to:
* Create a database of individuals, their time-stamped events, the groups they belong to.
* Enable to “replay a visit”
* Create a visit summary for a group or an individual
* Enable the presentation of visitors’ behavior statistics (as noted above and any creative idea you may have).
* Write a summary report

## 3. Material
For that you get:
* A museum floor plan with names of POIs (enlarge to see) + an excel file with some corrections.
* Visit logs – timestamped positions visited, timestamped presentations viewed with an indication if they ended normally (by the system) or terminated by the user and a list of time stamped events (less relevant).
* An excel file that associates the visitor number (“A” column, log file number) with the visitor’s group (“B” column) other columns are not relevant.

## 4. Delivery
I need a link to get a project report + a web-based system (for playback) + a user guide (better to have online help).  
Deliverables:
1. Project report
    * What you did
    * What technologies you used
    * What did you learn
    * Comments, suggestions, reflection
2. Link to a working web-based system that enables its user to:
    * Re-play (playback) visit for selected individuals/groups
    * Analyze visitors’ behavior
        * Various statistics as described above

[1]: https://doi.org/10.1007/s00779-016-0994-9 "Lanir, J., Kuflik, T., Sheidin, J., Yavin, N., Leiderman, K., & Segal, M. (2017). Visualizing museum visitors’ behavior: Where do they go and what do they do there?. Personal and Ubiquitous Computing, 21(2), 313-326."