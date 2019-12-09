<template>
  <v-layout justify-center align-center>
    <v-flex xs12 sm10 md10 lg10 xl10>
      <div class="text-center"></div>
      <v-card>
        <v-card-title class="headline">목록</v-card-title>
        <v-card-text>
            <v-data-table
              :headers="headers"
              :items="items"
              item-key="postId"
              :options.sync="options"              
              :server-items-length="totalElements"
              :loading="loading"
              class="elevation-1"
            >
              <template v-slot:item.createdAt="{ item }">{{ item.createdAt | toDateFormat}}</template>
              <template v-slot:item.modifiedAt="{ item }">{{ item.modifiedAt | toDateFormat}}</template>
              <template v-slot:item.action="{ item }">
                <v-btn class="mr-2" @click.stop="editItem(item)">수정</v-btn>
                <v-btn @click.stop="deleteItem(item)">삭제</v-btn>
              </template>              
            </v-data-table>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn tile color="primary" nuxt to="/new">등록</v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
import moment from "moment";

export default {
  data() {
    return {
      totalPages:1,
      totalElements:0,      
      items:[],
      loading: true,
      options: {
        sortBy : ["createdAt"], 
        sortDesc :[true], 
        page:1,
        itemsPerPage:10
      },      
      headers: [
        { text: '번호', value: 'postId' },
        { text: '제목', value: 'title', width:"10%" },
        { text: '내용', value: 'content', width:"40%" },        
        { text: '작성일', value: 'createdAt' },
        { text: '작성자', value: 'createdBy' },
        { text: '최종수정일', value: 'modifiedAt' },
        { text: '#', value: 'action', align:"center", sortable: false },        
      ],      
    }
  },
  filters: {
    toDateFormat: function(value) {
        const fromDate = moment(value);
        if (fromDate.isValid()) {
          return fromDate.format("YYYY-MM-DD");
        }
        console.log(fromDate);
        return "";
    },
  },
  created() {},
  mounted() {
    this.loadData();    
  },
  watch: {
    options: {
      handler () {
        this.loadData();
      },
      deep: true,
    },
  },  
  methods: {
    // 리스트 데이터 로드
    async loadData() {
      this.loading = true;
      this.items = [];
      const { sortBy, sortDesc, page, itemsPerPage } = this.options;
      const data = await this.$axios.$get(`/api/posts`, {
        params: {
          page: page,
          size: itemsPerPage,
          direction: (sortDesc[0] ? "DESC" : "ASC"),
          sortBy:sortBy[0]
        }
      });
      this.loading = false;
      this.totalElements = data.totalElements;
      this.items = data.content;
    },
    async deleteItem(item){
      const data = await this.$axios.$delete(`/api/posts/${item.postId}`);
      await this.loadData();
    },
    editItem(item){
        this.$router.push(`/posts/${item.postId}`)
    }
  }  
}
</script>
