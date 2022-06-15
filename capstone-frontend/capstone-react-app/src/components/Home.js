import React, { useState, useEffect } from "react";
import Twelve from "./Twelve.png";
import images from "./images";
import ImageSlider from "./imageSlider";
import image1 from "./image1.png";

export default function Home() {
    return (
      <main style={{ padding: "2rem" }}>
        <div>
          <img className="logo" src={Twelve} style={{padding: "0rem", width:300, height:300}} />
          <ImageSlider images={images}/>
        </div>
        <div>
        <span class="yelp-review" data-review-id="WDjunj7EP_X9z18z_4cg_w" 
        data-hostname="www.yelp.com">Read 
        <a href="https://www.yelp.com/user_details?userid=3kffGUf3oz__Sc8MZgQWlA" 
        rel="nofollow noopener">Luana O.</a>'s 
        <a href="https://www.yelp.com/biz/don-julios-mexican-kitchen-and-tequila-bar-lake-mary?hrid=WDjunj7EP_X9z18z_4cg_w" 
        rel="nofollow noopener">review</a> of 
        <a href="https://www.yelp.com/biz/Hm3k86w9CHNAR7dR1oJwWA"
        rel="nofollow noopener">Don Julios Mexican Kitchen & Tequila Bar</a> on 
        <a href="https://www.yelp.com" rel="nofollow noopener"
        >Yelp</a><script src="https://www.yelp.com/embed/widgets.js" 
        type="text/javascript" async></script></span>
        
        </div>
      </main>
    );
  }