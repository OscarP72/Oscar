import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { ProductComponent } from './componentes/product/product.component';
import { ProfileComponent } from './componentes/profile/profile.component';
import { OfferComponent } from './componentes/offer/offer.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent },
  {path: 'product', component: ProductComponent },
  {path: 'profile', component: ProfileComponent },
  {path: 'offer', component: OfferComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
