import { AfterViewInit,Component, Input, OnInit, ViewChild  } from '@angular/core';
import { CardTypeService } from '../../servicios/card-type.service';
import { ModalCardTypesComponent } from '../../componentes/modal-card-types/modal-card-types.component';

@Component({
  selector: 'app-card-type',
  templateUrl: './card-type.component.html',
  styleUrls: ['./card-type.component.css']
})
export class CardTypeComponent implements OnInit{
  @ViewChild(ModalCardTypesComponent) modal: any
  id: number = 0
  titulo: string;
  tarjetas: any[];
  mensaje: string = ""
  @Input() eventoDelHijo: string = ""
  constructor(private service: CardTypeService) {
    this.titulo = "TIPOS DE MEDIO DE PAGO"
    this.tarjetas = [];
  }

  eliminar(id: any) {
    if (confirm("¿Esta seguro de borrar el tipo de documento?")) {
      this.service.delete("http://localhost:8080/once/cardTypes/" + id)
        .subscribe((dato: boolean) => {
          if (!dato) {
            this.mensaje = "Se ha borrado correctamente"
            this.ngOnInit();
          }
          else
            this.mensaje = "El registro no se ha borrado"
        })
    }
  }


  realizarComunicacion(event: any) {
    //this.eventoDelHijo=event.salida
    this.mensaje = ""
    if (event.salida === "OK")
      this.ngOnInit();
  }

  ngOnInit(): void {

    this.tarjetas = []
    this.service.getDatos("http://localhost:8080/once/cardTypes")
      .subscribe((datos: any) => {
        this.tarjetas = datos._embedded.cardTypes;
      })
  }

  modificar(tarjeta: any) {
    this.mensaje = ""
    let ruta = tarjeta._links.self.href
    this.modal.id = parseInt(ruta.substring(ruta.lastIndexOf("/") + 1))
    // console.log(this.id )

  }

}