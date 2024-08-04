import React, { useState } from 'react';
import '../styles/FabButton.css';

type Props = {
    label: string,
    value: JSX.Element | string,
    action: () => void;
}

const FabButton: React.FC< { options: Props[] } > = ({ options }) =>{
  const [showOptions, setShowOptions] = useState(false);

  const toggleOptions = () => {
    setShowOptions(!showOptions);
  };

  return (
    <div className="fab-container">
      <button className={`fab main-fab ${showOptions ? 'show-options' : ''}`} onClick={toggleOptions}>
        +
      </button>
      <div className={`fab-options ${showOptions ? 'show' : ''}`}>

     
        {options.map((option, index) => (
        <div className="option-wrapper" key={index}>
            <label className="option-label">{option.label}</label>
            <button className="option" onClick={option.action}> {option.value} </button>
        </div>
        ))}
      </div>
    </div>
  );
};

export default FabButton;