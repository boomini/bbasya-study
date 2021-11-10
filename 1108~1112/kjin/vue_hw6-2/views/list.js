export default{
  template:`
  <div>
    <h2>코로나 19 예방접종센터 목록</h2>
    <span>지역</span> <select name="" id="" v-model="sido">
      <option value="전체" selected>전체</option>
      <option value="서울특별시">서울특별시</option>
      <option value="경기도">경기도</option>
      <option value="강원도">강원도</option>
      <option value="인천광역시">인천광역시</option>
      <option value="광주광역시">광주광역시</option>
      <option value="부산광역시">부산광역시</option>
      <option value="울산광역시">울산광역시</option>
      <option value="대구광역시">대구광역시</option>
      <option value="대전광역시">대전광역시</option>
      <option value="충청남도">충청남도</option>
      <option value="충청북도">충청북도</option>
      <option value="경상남도">경상남도</option>
      <option value="경상북도">경상북도</option>
      <option value="전라남도">전라남도</option>
      <option value="전라북도">전라북도</option>
      <option value="제주특별자치도">제주특별자치도</option>
      <option value="세종특별자치시">세종특별자치시</option>
    </select>
    <table border="1">
      <thead align="center">
        <tr>
          <th>번호</th>
          <th>센터명</th>
          <th>생성일 </th>
          <th>수정일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(center, index) in matchSido" :key="index">
          <td align="center">{{center.id}}</td>
          <td><router-link :to="'/detail/'+center.id+'/'+perPage">{{center.centerName}}</router-link></td>
          <td>{{center.createdAt}}</td>
          <td>{{center.updatedAt}}</td>
        </tr>
      </tbody>
    </table>
    <button @click="more">더보기</button>
    <button @click="less">줄이기</button>
    <button @click="clear">초기화</button>
  </div>
 
  `,
  data(){
    return {
      centerList,
      sido: "",
      perPage:10
    }
  },
  computed: {
    matchSido(){
      if (this.sido=="전체") return this.centerList.data;
      return this.centerList.data.filter(center=>center.sido==this.sido);
    }
  },
  created(){
    this.getData();
  },
  methods:{
    more(){
      this.perPage+=10;
      this.getData();
    },
    less(){
      this.perPage-=10;
      this.getData();
    },
    clear(){
      this.perPage=10;
      this.getData();
    },
    getData(){
      console.log(this.perPage);
      const URL="https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage="+this.perPage+"&serviceKey=pK%2B5v%2BJKLaoeG%2FpUU1QVku5WJ7JKwySWiCvRbpo4StK4pBIuKHVrxEuibjXNZGTBVqhZeeoS8fzCbBRy7uCOCg%3D%3D";
      axios.get(URL).then((response)=>{
        console.log(response);
        this.centerList=response.data;
      })
    }
  }
}