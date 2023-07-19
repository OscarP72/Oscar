import { Component, DoCheck, EventEmitter, Output } from '@angular/core';
import SubcategoryBean from '../../beans/SubcategoryBean';
import { SubcategoryService } from 'src/app/servicios/subcategories.service';

@Component({
  selector: 'app-modal-subcategory',
  templateUrl: './modal-subcategory.component.html',
  styleUrls: ['./modal-subcategory.component.css']
})
export class ModalSubcategoryComponent  {
  id: number = 0
  descripcion: string
  mensaje: string = "";
  fin: boolean = false
  subcategory: string = ""
  subcategoryPlaceHolder: string = ""
  subcategory_id: string = ""
  categoria: string = "0"
  categorias: Array<any>
  @Output() eventoAComunicar = new EventEmitter();
  antiguoId: number = this.id
  subtitulo: string = ""
subcategoriasCargadas:boolean=false


  constructor(private service: SubcategoryService) {
    this.descripcion = "";
    this.subcategory_id = "";
    this.categorias = []
  }
  ngOnInit(): void {
    if(this.id===0){
      this.subtitulo = "ALTA";
      this.subcategory="";
      this.subcategoryPlaceHolder="";
      this.categoria="0";
    this.service.getDatos("http://localhost:8080/once/categories")
      .subscribe((datos: any) => {
        this.categorias = datos._embedded.categories
        this.subcategoriasCargadas = true
      });
    }
      else{
        this.subtitulo = "MODIFICACION";
      this.service.getDatos("http://localhost:8080/once/subcategories/" + this.id)
            .subscribe((datos: any) => {
              this.categoria = datos._links.category.href.substring(datos._links.category.href.lastIndexOf('/') + 1);
              this.subcategoryPlaceHolder = datos.description;
              this.descripcion = datos.description;
              console.log("categoria " + this.categoria);
            });
          }
  }
 
  realizarComunicacion() {
  
    this.eventoAComunicar.emit({ salida: "OK" });
  
  }

  grabar() {
    this.fin = false;
    if (this.descripcion.trim() !== "") {
      this.service.saveOrUpdate("http://localhost:8080/once/subcategories",
        new SubcategoryBean(this.id, this.descripcion, "http://localhost:8080/once/categories/" + this.categoria))
        .subscribe((dato: boolean) => {
          if (dato) {
            this.mensaje = "Grabación realizada correctamente";
            this.subcategoryPlaceHolder = "";
          } else {
            this.mensaje = "La grabación no se ha realizado";
          }
        });
        this.realizarComunicacion()
    } else {
      this.mensaje = "Debes introducir un valor";
    }
  }
}