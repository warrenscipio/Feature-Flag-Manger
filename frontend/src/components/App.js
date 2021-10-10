import React,  { useState } from 'react';
import FeatureTracker from '../components/FeatureTracker/featureTracker';

 function App() {
    const [featureFlags, setData] = useState([]);

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
            setData(response);
          })
          .catch(err => {
            console.log('Check the Server...');
            console.log(err); 
          });
    }

    if(featureFlags.length !=0){
        //reorder the regions while I care about order
        featureFlags.forEach(feature =>{
            let orderedRegions = {asia:null,korea:null,eurpoe:null, japan:null,america:null};
            feature.regionValues = Object.assign(orderedRegions, feature.regionValues);
        })
        console.log(featureFlags);
    
        return (
          <div >
              {/*<FeatureDropdown featureArr={featureFlags}/>*/}
              <FeatureTracker featureFlags={featureFlags}/>
          </div>
        );
    } else {
        getData();
    }

    return("something died check console or loading")

  }

  
export default App;
