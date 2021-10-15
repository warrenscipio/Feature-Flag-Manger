import React,  { useState } from 'react';
import FeatureTracker from '../components/FeatureTracker/featureTracker';

 function App() {
    const [featureFlags, setData] = useState([]);
    const [noFeatures, setHasNoFeatures] = useState(false);

     //TODO move to a services.js file where the post would be as well
     function getData() {
        const url = "http://localhost:12300/";
    
        fetch("/features",{
            headers: {
                'Content-Type': 'application/json',
              }
        })
          .then(response => response.json())
          .then(response => {
            if(response.length != 0){
                setData(response);
            } else {
                setHasNoFeatures(true)
            }
            
          })
          .catch(err => {
            console.log('Check the Server...');
            console.log(err); 
          });
    }

    //post call
    function updateFeature() {
        const url = "http://localhost:12300/";
    
        fetch("/features",{
            headers: {
                'Content-Type': 'application/json',
              }
        })
          .then(response => response.json())
          .then(response => {
            if(response.length != 0){
                setData(response);
            } else {
                setHasNoFeatures(true)
            }
            
          })
          .catch(err => {
            console.log('Check the Server...');
            console.log(err); 
          });
    }

    let featuresRetrievedMsg = "Feature Manager retrieved: " + featureFlags.length;

    if(featureFlags.length !=0){
        
        console.log(featureFlags);
        console.log(featuresRetrievedMsg);
        return (
          <div >
              {/*<FeatureDropdown featureArr={featureFlags}/>*/}
              <FeatureTracker featureFlags={featureFlags}/>
          </div>
        );
    } else {
        if(noFeatures) {
            return(<div>{featuresRetrievedMsg}</div>)

        } else {
            getData();
        }
    }

    return("Loading...or something died check console ")

  }

  
export default App;
