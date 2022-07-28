# PetList
It will display list of Pet with images and their descriptions

The application has mostly three parts
1. First part is to Fetch data from pets_list.json and config.json via DataRepository
    - Data will be read and converted to Json Pojo
   
2. Second part is Ui part that contains listing adapter and screens
    - List adapter is adapter used to list Pet pojos in main screens
    - On list item click details screen will be displayed 
    - Details screen has image and web view implementation of "Content URL" 
  
3. Third part is Work Hour manger which will handle working hours.
    - Work hour manger hanldes time to handle screen time depending upon Configuration fetch from config.json.
    - It also register broadcast reciver on "TIME-TICK","TIME-CHANGED" and "DATE_CHAGED" events to monitor screen time.
    - To pass events from BroadcastReciver to UI EventBus lib is used.
    - On the basis of this events Non-Cancelable diloag is displayed on Screens.

