import{SafeUrl}from "@angular/platform-browser"
export interface FileHandle {
file: File,
url: SafeUrl
}

export interface Product {
productName:string,
productDescription: string, 
productDiscountedPrice: number,
productActualPrice: number,
productImages:FileHandle[]
}