using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;

namespace ISoleckiProductEF
{
    class Program
    {
        static void Main(string[] args)
        {
            //Console.WriteLine("Please, specify name of a product:");
            //string prodName = Console.ReadLine();
            //Product prod = new Product { ProductName = prodName };

            //Console.WriteLine("Please, specify name of a Supplier:");
            //string companyName = Console.ReadLine();
            //Supplier supp = new Supplier { CompanyName = companyName };

            ProdContext db = new ProdContext();
            //db.Products.Add(prod);
            //db.Suppliers.Add(supp);


            Product prodChange = db.Products.Single(p => p.ProductID == 1);
            Supplier supplier = db.Suppliers.Single(s => s.SupplierID == 1);

            //Console.WriteLine(supplier.SupplierID.ToString());

            prodChange.Suppliers = supplier;
            db.Products.Update(prodChange);
            db.SaveChanges();

            IQueryable<Product> query =
                from p in db.Products
                where p.Suppliers != null
                orderby p.ProductID
                select p;

            Console.WriteLine("\nList of products:");
            foreach (var item in query)
            {
                Console.WriteLine(item.ProductName + ": " + item.ProductID.ToString());
                Console.WriteLine("SupplierID: " + item.Suppliers.SupplierID.ToString());
            }


            IQueryable<Supplier> query2 =
                from s in db.Suppliers
                orderby s.CompanyName
                select s;

            Console.WriteLine("\nList of Suppliers:");
            foreach (var item in query2)
            {
                Console.WriteLine(item.CompanyName + ": " + item.SupplierID.ToString());
            }
        }
    }
    
    class Product
    {
        public int ProductID { get; set; }
        public string ProductName { get; set; }
        public int UnitsInStock { get; set; }
        
        [ForeignKey("SupplierID")]
        //TODO: changr to Supplier
        public Supplier Suppliers { get; set; }

    }

    class Supplier
    {
        public int SupplierID { get; set; }
        public string CompanyName { get; set; }
        public string Street { get; set; }
        public string City { get; set; }
        public ICollection<Product> Products { set; get; }
    }

    class ProdContext:DbContext
    {
        public DbSet<Product> Products { set; get; }
        public DbSet<Supplier> Suppliers { set; get; }
        protected override void OnConfiguring(DbContextOptionsBuilder options) => options.UseSqlite("DataSource=Product.db");
    }
}

