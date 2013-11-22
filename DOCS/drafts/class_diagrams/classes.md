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
    + toJson()

Main : object
    - tours Tour[]
    + main(args String[])

Screen : AndroidScreen
    - title
    - backSlide
