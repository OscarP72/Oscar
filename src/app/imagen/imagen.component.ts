import { Component } from '@angular/core';

@Component({
  selector: 'app-imagen',
  templateUrl: './imagen.component.html',
  styleUrls: ['./imagen.component.css']
})
export class ImagenComponent {

  imagen:string=""

  ejecutar(){
    console.log(this.imagen)
  }
}
