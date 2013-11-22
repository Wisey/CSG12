Android
=======

Location : object
    - name
    - lat
    - long
    - image
    - description
    - time
    + toJson()

Tour : object
    - name
    - locations
    - description
    - long description
    - date
    + toJson()
    + addLocation(location : Location)

Main : object
    - tours : Tour[]
    + main(args String[])

Screen : AndroidScreen
    - title
    - backSlide

TourCreator : AndroidScreen
    - tour : Tour
    - button/text field for each input
    - accept button
    - method for data retrieval from the user
    - getTour()

Class for each screen in the UI
