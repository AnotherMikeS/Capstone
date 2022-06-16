import React, { useState, useEffect } from "react";

import images from "./images";
import ImageSlider from "./imageSlider";
import yoshi from "./yoshi-tpt.png";

export default function Home() {
    return (
      <main style={{ padding: "1rem" }}>
        <div>
          <ImageSlider images={images}/>
      </div>
      
<div>
[um-iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2988.2045149897094!2d-81.68259128439395!3d41.49984267925408!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8830fa7d31bc7e1d%3A0xd4fedf1ad239019!2sPlayhouse%20Square!5e0!3m2!1sen!2sus!4v1655398318304!5m2!1sen!2sus" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"]
</div>


      {/* <div className="yelp-review">
      <span class="yelp-review" data-review-id="WGeShu94I3QENyoeNMftRw" data-hostname="www.yelp.com"><iframe src="https://www.yelp.com/biz/playhouse-square-cleveland-2?hrid=WGeShu94I3QENyoeNMftRw" rel="nofollow noopener"></iframe></span>   </div>
       */}

<div className="calloutBox">
  <div className="calloutBox-header">NINTENDO: THE MUSICAL!</div>
  <img className="yoshi" src={yoshi} style={{ padding: "0rem", width: 250, height: 150 }} />
  <div className="calloutBox-container">Auditioning Now
  </div>
</div>
      </main>
    );
  }