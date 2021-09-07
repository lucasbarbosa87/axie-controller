package br.com.mercury.axieinfinityapi.utils


const val profileBriefQuery =
    "query GetProfileBrief {  profile {    ...ProfileBrief    __typename  }}fragment ProfileBrief " +
            "on AccountProfile {  accountId  addresses {    ...Addresses    __typename  }  email  " +
            "activated  name  settings {    unsubscribeNotificationEmail    __typename  }  " +
            "__typename}fragment Addresses on NetAddresses {  ethereum  tomo  loom  " +
            "ronin  __typename}"


const val axieBriefListQuery = "query GetAxieBriefList(\$auctionType: AuctionType, " +
        "\$criteria: AxieSearchCriteria, \$from: Int, \$sort: SortBy, \$size: Int, " +
        "\$owner: String) {  axies(auctionType: \$auctionType, criteria: \$criteria, " +
        "from: \$from, sort: \$sort, size: \$size, owner: \$owner) {    total    " +
        "results {      ...AxieBrief      __typename    }    __typename  }}fragment " +
        "AxieBrief on Axie {  id  name  stage  class  breedCount  image  title  battleInfo " +
        "{    banned    __typename  }  auction {    currentPrice    currentPriceUSD    " +
        "__typename  }  parts {    id    name    class    type    specialGenes    " +
        "__typename  }  __typename}"