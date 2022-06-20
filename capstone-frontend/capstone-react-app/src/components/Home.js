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

      <div className="map">
      <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2988.2043296050037!2d-81.6825912845413!3d41.49984669697875!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8830fa7d31bc7e1d%3A0xd4fedf1ad239019!2sPlayhouse%20Square!5e0!3m2!1sen!2sus!4v1655742959498!5m2!1sen!2sus" width="600" height="450" loading="lazy" referrerPolicy="no-referrer-when-downgrade"></iframe>
      </div>

    </main>

  );
}