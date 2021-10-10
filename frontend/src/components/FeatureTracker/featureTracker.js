import React from "react";
import "./featureTracker.css";

function FeatureTracker(props) {

    function renderFeatureRows() {

        function renderCheckBox(regions){


            let index =0;
            //TODO this depends on order of the keys.. not ideal 
            return regions.map(region =>{
                let checked ={}

                if(region[1]==1){
                    checked['defaultChecked'] = 'defaultChecked';
                }
                    
                return (<td key={index++}>
                    <label className ="input-container">
                        <input type="checkbox" {...checked}></input>
                        <span className ="custom-checkmark"></span>
                    </label></td>);
            })
        }

        return props.featureFlags.map(feature=>{
            
            return(<tr key={feature.name} className ="identity-info-row">
            <td className ="row-title">{feature.name}</td>

            {renderCheckBox(Object.entries(feature.regionValues))}</tr>)
        });
    }


    return (
        <div className ="container">
            <div className = "title-banner" >Feature Flag Manger</div>
          <table>
            <tbody>
              <tr>
                <th className ="row-title">Region</th>
                <th className ="region-title">Asia</th>
                <th className ="region-title">Korea</th>
                <th className ="region-title">Europe</th>
                <th className ="region-title">Japan</th>
                <th className ="region-title">America</th>
              </tr>
              {renderFeatureRows()}

            </tbody>
          </table>
          <div className ="button-row" >
              <input  id="cancel-button"type="button" value="Cancel"></input>
              <input id="save-button" type="button"value="Save"></input>
          </div>
        </div>
      );
}

export default FeatureTracker;
