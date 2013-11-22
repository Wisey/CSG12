Android
=======

#Location : object

        - name
        - lat
        - long
        - image
        - description
        - time
        - base64jpeg(image) : String
        + toJson() : String

#Tour : object

        - name
        - locations
        - description
        - long description
        - date
        - uploaded
        + toJson() : String
        + addLocation(location : Location)

#Main : object

        - tours : Tour[]
        + main(args String[])

#TourUploader : object

        - tours : Tour[]
        + upload() : boolean

#MapScreen : AndroidScreen

#Settings : AndroidScreen

#TourCreator : AndroidScreen

        - tour : Tour
        - button/text field for each input
        - accept button
        - method for data retrieval from the user
        - getTour() Tour

#TourViewer : AndroidScreen

        - tour : Tour[]

#LocationViewer : AndroidScreen

        - locations : Locations[]

#Class for each screen in the UI
