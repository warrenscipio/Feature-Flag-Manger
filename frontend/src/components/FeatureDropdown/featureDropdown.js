/**Ended up not using this class but the idea was to also have a drop down with the features in it
 * to quickly select a feature a view it alone. Would probably update to a Search once I got it working.
 */

import React from "react";
import ReactDOM from "react-dom";
import Dropdown from 'react-dropdown';
import './featureDropdown.css';
import FeatureTracker from '../FeatureTracker/featureTracker';



function FeatureDropdown(props){
    let featureBitMaps;
let featureMapValues = new Map();

    //call backend?
    function getBitMap(selected){



        ReactDOM.render(<FeatureTracker name={selected.value} />, document.getElementById("feature-tracker"));

        return {'Asia':0,'Korea':0,'Europe': 0,'Japan': 1,'America': 1}
    }

    let featureArr = props.featureArr;
    let options = [];


    featureArr.forEach(element=>{
        featureMapValues.set(element.name, element.value);
        options.push({label:element.name, value: element.name})
    });


      const element = <div id="feature-dropdown">
          <Dropdown options={options}  onChange={getBitMap} placeholder="Select a feature" /></div>;
      return element
}




export default FeatureDropdown;
