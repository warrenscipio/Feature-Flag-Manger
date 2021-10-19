


export function updateFeatures(features, currentFeatureId, checked) {
    console.log(features)
    console.log(currentFeatureId)
    console.log(checked)

    //TODO further validation on feature names need to be done to make sure feature names dont have '-' in them.
    //or different split needs to happen.
    let splitId = currentFeatureId.split('-');
    let featureName = splitId[0];
    let regionName = splitId[1];

    for(var i=0; i<features.length; i++) {
        if(features[i].name == featureName){
            let regions = features[i].regionValues;
            for(var j=0; j<regions.length; j++){
                if(regions[j].regionName == regionName){
                    regions[j].regionValue = checked ? 1 : 0;
                }
            }
        }

    }

    return features;
}


export function saveCurrentFeatureValues(unsavedFeatures) {

    fetch("/save-features", {
        method: "POST", 
        body: unsavedFeatures,
        headers: {
            'Content-Type': 'application/json',
          }
    }).then(res => {
    
        //TODO handle the different errors in catch or in error handling file that looks at status codes
        console.log("response:", res);
        console.log("data was saved, but not really since theres no DB");

    });
        

}