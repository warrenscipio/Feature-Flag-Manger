import React from "react";
import "./featureTracker.css";
import {updateFeatures, saveCurrentFeatureValues} from "./featureUtility.js"

/**
 * Rendering of the Feature Manger.
 * @param {*} props 
 * @returns JSX.Element
 */
function FeatureTracker(props) {
    let unsavedFeatureFlags;





    function saveButtonClicked() {
        console.log("save")
        console.log(unsavedFeatureFlags)
        saveCurrentFeatureValues(unsavedFeatureFlags);
        
    }

    function cancelButtonClicked() {
        console.log("cancel")

    }

    function featureClicked(e) {
        console.log("feature "+ e.target.id+" was checked or unchecked");
        unsavedFeatureFlags = updateFeatures(props.featureFlags, e.target.id, e.target.checked);
    }


    function renderRegionTitleRow() {

        let key = 0;
        return props.featureFlags[0].regionValues.map(region=>{
            
            return(<th key={key++} className ="region-title">{region.regionName}</th>)
        });
    }

    /**
     * Render the new feature row using the featureFlags prop
     * @returns JSX.Element
     */
    function renderFeatureRows() {

        /**
         * Render the different values of the region checkboxes depending on the values in the map.
         * @param {*} regions Map of the regions 
         * @param {*} featureName name of the current row's feature.
         * @returns JSX.Element
         */
        function renderCheckBox(regions, featureName){

            let index = 0;
            //TODO this depends on order of the keys.. not ideal 
            return regions.map(region =>{
                let checked ={}

                if(region.regionValue){
                    checked['defaultChecked'] = 'defaultChecked';
                }
                    
                return (<td key={index++}>
                    <label className ="input-container">
                        <input id = {featureName +'-'+ region.regionName} onClick={featureClicked} type="checkbox" {...checked}></input>
                        <span className ="custom-checkmark"></span>
                    </label></td>);
            })
        }

        return props.featureFlags.map(feature=>{
            
            return(<tr key={feature.name} className ="identity-info-row">
            <td className ="row-title">{feature.name}</td>

            {renderCheckBox(feature.regionValues, feature.name)}</tr>)
        });
    }

    return (
        <div className ="container">
            <div className = "title-banner" >Feature Flag Manger</div>
          <table>
            <tbody>
              <tr>
                <th className ="row-title">Region</th>
                {renderRegionTitleRow()}
              </tr>
              {renderFeatureRows()}
            </tbody>
          </table>
          <div className ="button-row" >
              <input  id="cancel-button" type="button" value="Cancel" onClick={cancelButtonClicked}></input>
              <input id="save-button" type="button"value="Save" onClick={saveButtonClicked}></input>
          </div>
        </div>
      );
}

export default FeatureTracker;
