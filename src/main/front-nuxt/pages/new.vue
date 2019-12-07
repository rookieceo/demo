<template>
  <v-layout justify-center align-center>
    <v-flex xs12 sm8 md6>
      <div class="text-center"></div>
      <v-card>
        <v-card-title class="headline">등록</v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" xs="12">
                <v-text-field label="제목"
                    v-model="post.title"
                    v-validate="'required'"
                    data-vv-as="[제목]"
                    data-vv-name="title"                        
                    :error-messages="errors.collect('title')"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" xs="12">
                <v-textarea label="내용" 
                    auto-grow
                    v-model="post.content"
                    v-validate="'required'"
                    data-vv-as="[내용]"
                    data-vv-name="content"                        
                    :error-messages="errors.collect('content')"                
                ></v-textarea>                
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" xs="12">
                <v-text-field label="작성자"
                    v-model="post.createdBy"
                    v-validate="'required'"
                    data-vv-as="[작성자]"
                    data-vv-name="createdBy"                        
                    :error-messages="errors.collect('createdBy')"                
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-btn x-large tile color="primary" @click.stop="save">저장</v-btn>
          <v-spacer />
          <v-btn x-large tile color="error" nuxt to="/">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
import moment from 'moment'

export default {
  // 폼 유효성 검사기(vee-validate)
  $_veeValidate: {
    validator: 'new' // give me my own validator scope.
  },
  data() {
    return {
      post: {
        createdBy: '',
        content: '',
        title: ''
      }
    }
  },
  created() {},
  mounted() {},
  methods: {
    save() {
      this.$validator.validateAll().then(async result => {
        if (result) {
          try {
            const data = await this.$axios.$post('/api/posts', this.post)
            this.$router.push('/')
          } catch (err) {
            throw err
          }
        }
      })
    }
  }
}
</script>
