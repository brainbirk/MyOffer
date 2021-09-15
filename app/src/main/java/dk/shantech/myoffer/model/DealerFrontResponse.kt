package dk.shantech.myoffer.model

class DealerFrontResponse : ArrayList<DealerFrontResponseItem>()

data class DealerFrontResponseItem(
    val catalogs: List<Catalog>,
    val dealer: Dealer
)

data class Catalog(
    val all_stores: Boolean,
    val background: String,
    val branding: Branding,
    val category_ids: List<Any>,
    val dealer_id: String,
    val dealer_url: String,
    val dimensions: Dimensions,
    val ern: String,
    val id: String,
    val images: Images,
    val incito_publication_id: Any,
    val label: String,
    val offer_count: Int,
    val page_count: Int,
    val pages: Pages,
    val pdf_url: Any,
    val run_from: String,
    val run_till: String,
    val store_id: Any,
    val store_url: Any,
    val types: List<String>
)

data class Dealer(
    val category_ids: List<Any>,
    val color: String,
    val country: Country,
    val description: String,
    val description_markdown: String,
    val ern: String,
    val facebook_page_id: Any,
    val favorite_count: Int,
    val graph_id: Any,
    val id: String,
    val logo: String,
    val name: String,
    val pageflip: PageflipX,
    val twitter_handle: Any,
    val website: String,
    val youtube_user_id: Any
)

data class Branding(
    val color: String,
    val description: String,
    val logo: String,
    val name: String,
    val pageflip: Pageflip,
    val website: String
)

data class Dimensions(
    val height: Double,
    val width: Int
)

data class Images(
    val thumb: String,
    val view: String,
    val zoom: String
)

data class Pages(
    val thumb: List<Any>,
    val view: List<Any>,
    val zoom: List<Any>
)

data class Pageflip(
    val color: String,
    val logo: String
)

data class Country(
    val id: String,
    val unsubscribe_print_url: Any
)

data class PageflipX(
    val color: String,
    val logo: String
)