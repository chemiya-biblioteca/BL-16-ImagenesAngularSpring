import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { map } from 'rxjs';
import { FileHandle, Product } from './app.model';
import { ConexionService } from './conexion.service';
import { ImageProcessingService } from './image-processing.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  products!:Product[];
  product: Product = { productName: "prueba",
  productDescription:"prueba",
  productDiscountedPrice:0,
  productActualPrice: 0,
  productImages: []
  }
  constructor (private productService: ConexionService,
  private sanitizer: DomSanitizer, private imageProcessingService:ImageProcessingService) { }


  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getAllProducts()
    
  }

  addProduct () { 
    const productFormData = this.prepareFormData(this.product);
    this.productService.addProduct(productFormData).subscribe(
    (response: Product) => {
   
    },
    (error: HttpErrorResponse) => {
    console.log(error);
   
  }
    );


  }

  prepareFormData(product: Product): FormData{

   const formData = new FormData();

  formData.append(
  'product',
  new Blob ([JSON.stringify(product)], {type: 'application/json'}) );
  for(var i = 0; i < product.productImages.length; i++) {
  formData.append( 'imageFile',
  product.productImages [i].file, product.productImages [i].file.name );
  }
  return formData;
}



onFileSelected(event:any) {
  if (event.target.files) { 
    const file = event.target.files[0];
    const fileHandle: FileHandle ={ file: file,
  
    url: this.sanitizer.bypassSecurityTrustUrl( window.URL.createObjectURL(file))
    }
  this.product.productImages.push(fileHandle);
  }


console.log("tamano:"+this.product.productImages.length)

}


public getAllProducts() {
  this.productService.getAllProducts().pipe(map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
  ).subscribe(
  (resp: Product []) => {
  console.log(resp);
  this.products = resp;
  }, (error: HttpErrorResponse) => {
  console.log(error);
  }
  );
  }


}

