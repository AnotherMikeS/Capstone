import React, { useState, useEffect } from "react";
import images from "./images";
import ImageSlider from "./imageSlider";
import yoshi from "../Images/yoshi-tpt.png";

export default function Home() {
  
  
  return (
    <main style={{ padding: "1rem" }}>
      <div>
        <ImageSlider images={images} />
      </div>

      <div className="calloutBox">
        <div className="calloutBox-header">NINTENDO: THE MUSICAL!</div>
        <img className="yoshi" src={yoshi} style={{ padding: "0rem", width: 250, height: 150 }} />
        <div className="calloutBox-container">Auditioning Now
        </div>
      </div>
    </main>

  );
}