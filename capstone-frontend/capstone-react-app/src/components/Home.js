import React, { useState, useEffect } from "react";

import images from "./images";
import ImageSlider from "./imageSlider";

export default function Home() {
    return (
      <main style={{ padding: "1rem" }}>
        <div>
          <ImageSlider images={images}/>
      </div>
      
      {/* <div className="yelp-review">
      <span class="yelp-review" data-review-id="WGeShu94I3QENyoeNMftRw" data-hostname="www.yelp.com"><iframe src="https://www.yelp.com/biz/playhouse-square-cleveland-2?hrid=WGeShu94I3QENyoeNMftRw" rel="nofollow noopener"></iframe></span>   </div>
       */}

<div className="calloutBox">
  <div className="calloutBox-header">NINTENDO: the musical!</div>
  <div className="calloutBox-container">auditioning now
  </div>
</div>
      </main>
    );
  }