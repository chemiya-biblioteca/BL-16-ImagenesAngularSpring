import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './app.model';

@Injectable({
  providedIn: 'root'
})
export class ConexionService {

  constructor(private httpClient: HttpClient) { }
public addProduct (product: FormData) {
return this.httpClient.post<Product>("http://localhost:8080/users/addNewProduct", product);
}

getAllProducts() {
 
  return this.httpClient.get<Product[]>("http://localhost:8080/users/getAllProducts");
}
}



