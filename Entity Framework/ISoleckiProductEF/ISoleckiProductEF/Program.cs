using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
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
            ProdContext db = new ProdContext();

            //Console.WriteLine("Specify name of a product:");
            //string prodName = Console.ReadLine();
            //Product prod = new Product { ProductName = prodName };
            //Console.WriteLine("Specify name of a category:");
            //string cat = Console.ReadLine();
            //Category category = new Category { Name = cat };

            //Console.WriteLine("Specify name of a Supplier:");
            //string companyName = Console.ReadLine();
            //Supplier supp = new Supplier { CompanyName = companyName };


            //db.Suppliers.Add(supp);
            //db.Products.Add(prod);
            //db.Categories.Add(category);


            //Console.WriteLine("Specify ID of a Supplier :");
            //int sId = Int32.Parse(Console.ReadLine());
            //Console.WriteLine("Specify ID of a Product: ");
            //int pId = Int32.Parse(Console.ReadLine());
            //Console.WriteLine("Specify name of a category: ");
            //string catName = Console.ReadLine();

            //Supplier supplier = db.Suppliers.Single(s => s.SupplierID == sId);
            //Product prodChange = db.Products.First(p => p.ProductID == pId);
            //Category cat = db.Categories.First(c => c.Name == catName);

            //prodChange.Supplier = supplier;
            //supplier.Products.Add(prodChange);
            //prodChange.Category = cat;
            //cat.Products.Add(prodChange);



            //IQueryable<Category> query3 =
            //    from c in db.Categories
            //    orderby c.CategoryID
            //    select c;

            //Console.WriteLine("\nList of categories");
            //foreach (var item in query3)
            //{
            //    Console.WriteLine(item.CategoryID.ToString() + " - " + item.Name);
            //    if(item.Products != null)
            //    {
            //        Console.WriteLine("Products:");
            //        foreach (var prod2 in item.Products)
            //            Console.WriteLine("     " + prod2.ProductName);
            //    }
            //}

            //Product prod1 = new Product { ProductName = "Władca pierścieni: Drużyna Pierścienia" };
            //Product prod2 = new Product { ProductName = "Władca pierścieni: Dwie Wieże" };
            //Product prod3 = new Product { ProductName = "Władca pierścieni: Powrót Króla" };
            //Product prod4 = new Product { ProductName = "Metro 2033" };
            //Category cat = new Category { Name = "Books" };
            //cat.Products.Add(prod1);
            //cat.Products.Add(prod2);
            //cat.Products.Add(prod3);
            //cat.Products.Add(prod4);
            //prod1.Category = cat;
            //prod2.Category = cat;
            //prod3.Category = cat;
            //prod4.Category = cat;
            //Supplier sup = new Supplier { CompanyName = "Empik" };
            //prod1.Supplier = sup;
            //prod2.Supplier = sup;
            //prod3.Supplier = sup;
            //prod4.Supplier = sup;
            //sup.Products.Add(prod1);
            //sup.Products.Add(prod2);
            //sup.Products.Add(prod3);
            //sup.Products.Add(prod4);

            //db.Categories.Add(cat);
            //db.Products.Add(prod1);
            //db.Products.Add(prod2);
            //db.Products.Add(prod3);
            //db.Products.Add(prod4);
            //db.Suppliers.Add(sup);

            //Invoice inv1 = new Invoice { InvoiceNumber = 1, Quantity = 3 };
            //Invoice inv2 = new Invoice { InvoiceNumber = 2, Quantity = 2 };
            //db.Invoices.Add(inv1);
            //db.Invoices.Add(inv2);

            //InvoiceProduct invProd1 = new InvoiceProduct { Invoice = inv1, Product = prod1 };
            //inv1.InvoiceProducts.Add(invProd1);
            //InvoiceProduct invProd2 = new InvoiceProduct { Invoice = inv1, Product = prod2 };
            //inv1.InvoiceProducts.Add(invProd2);
            //InvoiceProduct invProd3 = new InvoiceProduct { Invoice = inv1, Product = prod3 };
            //inv1.InvoiceProducts.Add(invProd3);
            //InvoiceProduct invProd4 = new InvoiceProduct { Invoice = inv1, Product = prod4 };
            //inv1.InvoiceProducts.Add(invProd4);
            //prod1.InvoiceProduct.Add(invProd1);
            //prod2.InvoiceProduct.Add(invProd2);
            //prod3.InvoiceProduct.Add(invProd3);
            //prod4.InvoiceProduct.Add(invProd4);

            //InvoiceProduct invProd5 = new InvoiceProduct { Invoice = inv2, Product = prod4 };
            //inv2.InvoiceProducts.Add(invProd5);
            //prod4.InvoiceProduct.Add(invProd5);

            //for (int i = 0; i < 3; i++)
            //{
            //    Console.WriteLine("Name of the company: ");
            //    string name = Console.ReadLine();
            //    Console.WriteLine("City: ");
            //    string city = Console.ReadLine();
            //    Console.WriteLine("Street: ");
            //    string street = Console.ReadLine();
            //    Console.WriteLine("Zip code:");
            //    string zip = Console.ReadLine();
            //    Console.WriteLine("Discount: ");
            //    int discount = Int32.Parse(Console.ReadLine());

            //    Customer customer = new Customer
            //    {
            //        CompanyName = name,
            //        City = city,
            //        Street = street,
            //        ZipCode = zip,
            //        Discount = discount
            //    };

            //    db.Companies.Add(customer);
            //}

            //for (int i = 0; i < 3; i++)
            //{
            //    Console.WriteLine("Name of the company: ");
            //    string name = Console.ReadLine();
            //    Console.WriteLine("City: ");
            //    string city = Console.ReadLine();
            //    Console.WriteLine("Street: ");
            //    string street = Console.ReadLine();
            //    Console.WriteLine("Zip code:");
            //    string zip = Console.ReadLine();
            //    Console.WriteLine("Bank Account Number: ");
            //    int accNum = Int32.Parse(Console.ReadLine());

            //    Supplier supplier = new Supplier
            //    {
            //        CompanyName = name,
            //        City = city,
            //        Street = street,
            //        ZipCode = zip,
            //        BankAccountNumber = accNum

            //    };

            //    db.Companies.Add(supplier);
            //}

            db.SaveChanges();

            //var companies = db.Companies.OfType<Customer>();
            //foreach (var item in companies)
            //{
            //    Console.WriteLine("Name of the company: " + item.CompanyName);
            //    Console.WriteLine("City: " + item.City);
            //    Console.WriteLine("Street: " + item.Street);
            //    Console.WriteLine("Zip code:" + item.ZipCode);
            //    Console.WriteLine("Discount: " + item.Discount + "\n");
            //}

            var companies2 = db.Companies.OfType<Supplier>();
            foreach (var item in companies2)
            {
                Console.WriteLine("Name of the company: " + item.CompanyName);
                Console.WriteLine("City: " + item.City);
                Console.WriteLine("Street: " + item.Street);
                Console.WriteLine("Zip code:" + item.ZipCode);
                Console.WriteLine("Bank Account Number: " + item.BankAccountNumber + "\n");
            }





            //IQueryable<Product> query =
            //    from p in db.Products
            //    orderby p.ProductID
            //    select p;

            //Console.WriteLine("\nList of products:");
            //foreach (var item in query)
            //{
            //    Console.WriteLine(item.ProductID.ToString() + " - " + item.ProductName);
            //    if (item.Supplier != null)
            //        Console.WriteLine("     Supplier: " + item.Supplier.CompanyName);
            //    if (item.Category != null)
            //        Console.WriteLine("     Category: " + item.Category.Name);
            //}


            //IQueryable<Supplier> query2 =
            //    from s in db.Suppliers
            //    orderby s.CompanyName
            //    select s;

            //Console.WriteLine("\nList of suppliers:");
            //foreach (var item in query2)
            //{
            //    Console.WriteLine(item.SupplierID.ToString() + " - " + item.CompanyName);
            //    if (item.Products != null)
            //    {
            //        Console.WriteLine("Products:");
            //        foreach (var p in item.Products)
            //            Console.WriteLine("     " + p.ProductName);
            //    }

            //}


            //IQueryable<InvoiceProduct> query3 =
            //    from ip in db.InvoiceProducts
            //    select ip;

            //Console.WriteLine("\nList of Invoice - Product relationships:");
            //foreach (var item in query3)
            //{
            //    Console.WriteLine("InvoiceID: " + item.InvoiceID + ", ProductID:" + item.ProductID);
            //}


            //var prodsFromCat = db.Categories.Include(c => c.Products).Where(c => c.Name == "Games");
            //Console.WriteLine("Games:");
            //foreach (var c in prodsFromCat)
            //{
            //    foreach (var p in c.Products)
            //        Console.WriteLine("     " + p.ProductName);
            //} 



        }
    }

    class Product
    {
        public Product()
        {
            this.InvoiceProduct = new Collection<InvoiceProduct>();
        }
        public int ProductID { get; set; }
        public string ProductName { get; set; }
        public int UnitsInStock { get; set; }
        public Supplier Supplier { get; set; }
        public Category Category { get; set; }
        public ICollection<InvoiceProduct> InvoiceProduct { get; set; }
    }

    class Company
    {
        public int CompanyID { get; set; }
        public string CompanyName { get; set; }
        public string Street { get; set; }
        public string City { get; set; }
        public string ZipCode { get; set; }
    }

    [Table("Suppliers")]
    class Supplier : Company
    {
        public Supplier()
        {
            this.Products = new Collection<Product>();
        }

        public int BankAccountNumber { get; set; }
        public virtual ICollection<Product> Products { set; get; }
    }

    [Table("Customers")]
    class Customer : Company
    {
        public int Discount { get; set; }
    }

    class Category
    {
        public Category()
        {
            this.Products = new Collection<Product>();
        }
        public int CategoryID { set; get; }
        public string Name { set; get; }
        public ICollection<Product> Products { set; get; }
    }

    class InvoiceProduct
    {
        public int ProductID { get; set; }
        public Product Product { get; set; }
        public int InvoiceID { get; set; }
        public Invoice Invoice { get; set; }
    }

    class Invoice
    {
        public Invoice()
        {
            this.InvoiceProducts = new Collection<InvoiceProduct>();
        }
        public int InvoiceID { get; set; }
        public int InvoiceNumber { get; set; }
        public int Quantity { get; set; }
        public ICollection<InvoiceProduct> InvoiceProducts { get; set; }

    }

    class ProdContext:DbContext
    {
        public DbSet<Company> Companies { get; set; }
        public DbSet<Product> Products { set; get; }
        public DbSet<Category> Categories { set; get; }
        public DbSet<Invoice> Invoices { get; set; }
        public DbSet<InvoiceProduct> InvoiceProducts { get; set; }
        protected override void OnConfiguring(DbContextOptionsBuilder options) => 
            options.UseSqlite("DataSource=Product.db");
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<InvoiceProduct>().HasKey(x => new { x.ProductID, x.InvoiceID });
        }
    }
}

