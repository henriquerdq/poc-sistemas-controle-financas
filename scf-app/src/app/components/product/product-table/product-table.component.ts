import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { HeaderService } from '../../templates/header/header.service';
import { Product } from '../product.model';
import { ProductService } from '../product.service';
import { ProductTableDataSource} from './product-table-datasource';

@Component({
  selector: 'app-product-table',
  templateUrl: './product-table.component.html',
  styleUrls: ['./product-table.component.scss']
})
export class ProductTableComponent implements AfterViewInit, OnInit {

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<Product>;
  dataSource: ProductTableDataSource; 

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['ano', 'nome', 'action'];

  constructor(private productService: ProductService, private headerService: HeaderService) {
    headerService.headerData = {
      title: "Cadastro de Conta",
      icon: "storefront",
      routerUrl: "/contas"
    }
    this.dataSource = new ProductTableDataSource();
  }
  
  ngOnInit(): void {
    this.productService.read().subscribe(products => {
      this.dataSource.data = products;
    })
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }
}
